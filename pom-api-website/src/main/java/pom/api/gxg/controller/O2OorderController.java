package pom.api.gxg.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pom.api.gxg.Util.E3requestUtil;
import pom.api.gxg.Util.HttpRequest;
import pom.api.gxg.Util.JsonUtil;
import pom.api.gxg.Util.Log;
import pom.api.gxg.apiresult.Result;
import pom.api.gxg.apiresult.ResultGenerator;
import pom.api.gxg.service.account.domain.o2ostockDomain;
import pom.api.gxg.service.account.entity.o2o.BosPassE3o2oorderData;
import pom.api.gxg.service.account.entity.o2o.BosPassE3o2oorderitems;
import pom.api.gxg.service.account.entity.o2o.O2ObosStock;
import pom.api.gxg.service.account.entity.o2o.OraclePassMeo2oorder;
import pom.api.gxg.service.account.service.O2OStockService;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class O2OorderController {

    @Value("${e3.url}")
    private String url;


    @Value("${e3.key}")//"${e3.url}"
    private String key;

    @Value("${e3.secret}")
    private String secret;
    @Value("${e3.e3ver}")
    private String e3ver;

    @Autowired
    private O2OStockService o2OStockService;

    //云仓订单下发E3
    @RequestMapping(value = "/o2oorder.add", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public @ResponseBody
    Result O2OorderAdd(HttpServletRequest request) throws IOException {

        InputStream in = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }

        String datas = java.net.URLDecoder.decode(buffer.toString(),"utf-8");
        OraclePassMeo2oorder data=JSON.parseObject(datas, new TypeReference<OraclePassMeo2oorder>() {});
        try {

            BosPassE3o2oorderData orderdata = new BosPassE3o2oorderData();
            //订单号
            orderdata.setOrder_sn(data.getOrder_sn());
            //店铺代码
            orderdata.setSd_code(data.getStorecode());
            //收货人
            orderdata.setReceiver_name(data.getRECEIVER_NAME());
            //收货人省
            orderdata.setReceiver_province(data.getC_PROVINCE_ID__NAME());
            //收货人市
            orderdata.setReceiver_city(data.getC_CITY_ID__NAME());
            //收货人省区
            orderdata.setReceiver_district(data.getC_DISTRICT_ID__NAME());
            //收消息地址
            orderdata.setReceiver_address(data.getRECEIVER_ADDRESS());
            //邮政编码
            orderdata.setReceiver_zip(data.getRECEIVER_ZIP());
            //电话号码
            orderdata.setReceiver_tel(data.getRECEIVER_PHONE());
            //下单时间
            orderdata.setAdd_time(data.getCreatetime());
            //订单支付时间
            orderdata.setPay_time(data.getCashtime());
            //付款状态
            orderdata.setPay_status(1);
            //金额
            orderdata.setOrder_amount(data.getPaymount().get(0));

            orderdata.setPayment(data.getPaymount().get(0));
            List<BosPassE3o2oorderitems> list = new ArrayList<BosPassE3o2oorderitems>();
            //对比商品数量map
            HashMap<String, Integer> mapsku = new HashMap<String, Integer>();


            for (int i = 0; i < data.getProductno().size(); i++) {
                BosPassE3o2oorderitems items = new BosPassE3o2oorderitems();

                mapsku.put(data.getProductno().get(i), data.getQty().get(i));
                items.setOrder_sn(data.getOrder_sn());
                items.setSku(data.getProductno().get(i));//商品SKU
                items.setMarket_price(data.getPricelist().get(i));
                items.setShop_price(data.getPriceactual().get(i));
                items.setGoods_price(data.getPriceactual().get(i));
                items.setGoods_number(data.getQty().get(i));//商品数量
                if (data.getType().get(i).equals("3")) {
                    items.setIs_gift(1);
                } else {
                    items.setIs_gift(0);
                }
                list.add(items);


            }

            orderdata.setItems(list);
            //System.out.println(JsonUtil.obj2StringPretty(orderdata));
            Log.info("调用order.add接口json info request:" + JsonUtil.obj2String(orderdata));
            HashMap<String, String> hmParams = E3requestUtil.getE3dataRequest("order.add", JsonUtil.obj2String(orderdata),key,secret,e3ver);


            String e3result = HttpRequest.doPost(url, hmParams, null);

            if (e3result != null && !e3result.equals("")) {
                Log.info("order.add info result:" + e3result);

                JSONObject e3json = JSON.parseObject(e3result);

                try {

                    if (e3json.get("message").equals("success")) {//分单成功，查询bos库存，是否需要重新分单
                        boolean bool = false;
                        JSONArray e3data = JSONArray.parseArray(e3json.get("data").toString());
                        int count = 0;
                        while (bool) {
                            count++;
                            HashMap<String, Integer> mapstock = new HashMap<String, Integer>();
                            for (int i = 0; i < e3data.size(); i++) {


                                JSONObject jsondata = JSONObject.parseObject(e3data.get(i).toString());
                                O2ObosStock o2ObosStock = new O2ObosStock();
                                o2ObosStock.setOrder_sn(jsondata.getString("order_sn"));//"2040404"
                                o2ObosStock.setOuter_code(jsondata.getString("outer_code"));//"5165050600038"
                                //map.put("order_sn", jsondata.getString("order_sn"));
                                //map.put("outer_code", jsondata.getString("outer_code"));
                                Integer stock = Integer.valueOf(o2OStockService.getStock(o2ObosStock));
                                mapstock.put(jsondata.getString("outer_code"), stock);

                            }

                            for (String key : mapsku.keySet()) {

                                //库存数对比
                                if (mapsku.get(key) < mapstock.get(key)) {
                                    bool = true;
                                } else {
                                    //bos库存不足
                                    bool = false;

                                    try {
                                        JSONObject reject = new JSONObject();
                                        reject.put("orderCode", data.getOrder_sn());
                                        reject.put("Note", "自动重新指派");
                                        reject.put("orderStatus", "REJECT");
                                        SimpleDateFormat dfTemp = new SimpleDateFormat("yyyyMMddHHmmss");
                                        String curTime = dfTemp.format(new Date());
                                        reject.put("operateorTime", curTime);
                                        HashMap<String, String> hmreject = E3requestUtil.getE3dataRequest("UpdateSalesOrder", JsonUtil.obj2String(reject),key,secret,e3ver);
                                        String e3reject = HttpRequest.doPost(url, hmreject, null);

                                        if (e3reject != null && !e3reject.equals("")) {
                                            Log.info("UpdateSalesOrder info result:" + e3reject);
                                            JSONObject e3jsonreject = JSON.parseObject(e3reject);
                                            if (e3jsonreject.get("message").equals("success")) {

                                                //重新给对比库存的sku赋值
                                                e3data = JSON.parseArray(e3jsonreject.get("data").toString());

                                            } else {


                                                return ResultGenerator.genFailResult(e3jsonreject.get("message").toString());
                                            }

                                        } else {
                                            //接口返回null
                                            return ResultGenerator.genFailResult("调用E3接口UpdateSalesOrder失败返回null");
                                        }

                                    } catch (Exception e2) {
                                        //调用E3接口UpdateSalesOrder出现异常
                                        Log.error("调用E3接口UpdateSalesOrder失败返回null error :" + Log.getCrashMessage(e2));
                                        return ResultGenerator.genFailResult("调用UpdateSalesOrder自动重新派单接口出现异常！");
                                    }
                                    break;
                                }
                            }
                            if (count > 10)
                                bool = true;
                        }

                        //数据最后的处理！
                        //if (bool && count<=10) {
                        JSONArray jsondatas = new JSONArray();
                        JSONArray jsone3datas = e3json.getJSONArray("data");

                        for (int i = 0; i < jsone3datas.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("p_storecode", jsone3datas.getJSONObject(i).getString("order_sn"));
                            jsonObject.put("p_sku", jsone3datas.getJSONObject(i).getString("outer_code"));

                        }

                        return ResultGenerator.genSuccessResult(jsondatas);

                        //}else {
                        //死循环适配中-----


                        //}
                    } else {


                        //调用接口成功，但分单失败
                        return ResultGenerator.genFailResult(e3json.get("message").toString());
                    }

                } catch (Exception e1) {
                    Log.error("o2oorder.add error http:" + Log.getCrashMessage(e1));
                    //调用接口。解析接口数据异常
                    return ResultGenerator.genFailResult("调用E3接口成功。解析数据异常");
                }

            } else {
                //接口返回null
                return ResultGenerator.genFailResult("调用E3接口order.add失败返回null");
            }

        } catch (Exception e) {
            Log.error("o2oorder.add error :" + Log.getCrashMessage(e));
            //方法异常
            return ResultGenerator.genFailResult(Log.getCrashMessage(e));

        }

    }

    //云仓订单，库存查询
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/o2oorder.getstock", method = RequestMethod.POST,produces = "text/xml;charset=utf-8")
    public @ResponseBody
    Result getStock(@RequestBody o2ostockDomain o2ostockDomain){


        int num=0;

        for (int i = 0; i <o2ostockDomain.getP_sku().size() ; i++) {
            JSONObject data=new JSONObject();
            //条码
            String sku="";
            //店仓编号
            String code="";
            sku=o2ostockDomain.getP_sku().get(i);
            code=o2ostockDomain.getP_storecode().get(i);
            data.put("sku",sku);
            data.put("warehouseCode", code);
            data.put("pageSize", 100);
            data.put("pageNo", 1);
            System.out.println(data.toString());

            HashMap<String, String> hmParams = E3requestUtil.getE3dataRequest("stock.list.get", data.toString(),key,secret,e3ver);


            String e3result = HttpRequest.doPost(url, hmParams, null);
            System.out.println(e3result);

            JSONObject respjson=JSONObject.parseObject(e3result);
            if (respjson.getString("status").equals("api-success")) {//调用接口成功
                JSONObject respdata = respjson.getJSONObject("data");
                JSONArray respdataArray = respdata.getJSONArray("stockListGet");
                if (respdataArray != null) {

                    for (int j = 0; j < respdataArray.size(); j++) {

                        JSONObject jsonObject=respdataArray.getJSONObject(j);
                        num+=jsonObject.getInteger("kysl");
                    }

                } else {

                    num+=0;
                }

            }else {//调用接口返回错误信息


                return ResultGenerator.genFailResult(respjson.getString("message"));
            }

        }

        return ResultGenerator.getSuccessStock(num);
    }


}
