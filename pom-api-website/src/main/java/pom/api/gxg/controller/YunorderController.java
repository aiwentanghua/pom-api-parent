package pom.api.gxg.controller;


import com.alibaba.fastjson.JSON;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.pac.sdk.cp.PacClient;
import com.taobao.pac.sdk.cp.SendSysParams;
import com.taobao.pac.sdk.cp.dataobject.request.MERCHANT_CANCEL_ORDER.MerchantCancelOrderRequest;
import com.taobao.pac.sdk.cp.dataobject.request.MERCHANT_CANCEL_ORDER.OpenStoreCancelOrderRequest;
import com.taobao.pac.sdk.cp.dataobject.request.MERCHANT_CREATE_CAINIAO_ORDER.*;

import com.taobao.pac.sdk.cp.dataobject.request.MERCHANT_SEARCH_COURIER.ConsignRequest;
import com.taobao.pac.sdk.cp.dataobject.request.MERCHANT_SEARCH_COURIER.MerchantSearchCourierRequest;
import com.taobao.pac.sdk.cp.dataobject.response.MERCHANT_CANCEL_ORDER.MerchantCancelOrderResponse;
import com.taobao.pac.sdk.cp.dataobject.response.MERCHANT_CREATE_CAINIAO_ORDER.MerchantCreateCainiaoOrderResponse;

import com.taobao.pac.sdk.cp.dataobject.response.MERCHANT_SEARCH_COURIER.MerchantSearchCourierResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pom.api.gxg.Util.Log;
import pom.api.gxg.apiresult.Result;
import pom.api.gxg.apiresult.ResultGenerator;
import pom.api.gxg.service.account.domain.CaiNiaoorderDomain;
import pom.api.gxg.service.account.domain.CaiNiaoorderDomainItem;
import pom.api.gxg.service.account.domain.CaiNiaoorderendDomain;
import pom.api.gxg.service.account.entity.cainiao.BosPassMeYunorder;


import pom.api.gxg.service.account.service.OpenStoreCreateOrderService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class YunorderController {


    @Autowired
    private OpenStoreCreateOrderService openStoreCreateOrderService;

    @Value("${cainiao.appkey}")
    private String appkey;
    @Value("${cainiao.secretKey}")
    private String secretKey;
    @Value("${cainiao.url}")
    private String url;
    @Value("${cainiao.token}")
    private String token;
    @Value("${cainiao.sellerId}")
    private Long sellerId;

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/order.yun", method = RequestMethod.POST)
    public @ResponseBody
    Result orderYun(@RequestBody BosPassMeYunorder bosPassMeYunorder) {


        String yunorderid = bosPassMeYunorder.getId();//020门店发货单ID
        Log.info("发货订单ID：" + yunorderid);
        //获取发货单数据
        CaiNiaoorderDomain caiNiaoorderDomain = openStoreCreateOrderService.getorder(yunorderid);


        //商家id454291526
        //long sellerId = 454291526;

        PacClient pacClient = new PacClient(appkey, secretKey, url);
        pacClient.getLogger().setLogEnable(false);//关闭日志打印

        SendSysParams sendSysParams = new SendSysParams();
        sendSysParams.setFromCode(token);//一定要填写自己的cpcode , ISV请求填写token值

        /*MERCHANT_CREATE_CAINIAO_ORDER
                创建菜鸟配送订单*/
        OpenStoreCreateOrderRequest openStoreCreateOrderRequest = new OpenStoreCreateOrderRequest();

        openStoreCreateOrderRequest.setOrderSource(25L);//订单渠道
        openStoreCreateOrderRequest.setServiceCategoryCode("STORE_SEND_ITEM");//服务标准类目code
        openStoreCreateOrderRequest.setOrderChannelsType("OTHERS");//订单渠道
        openStoreCreateOrderRequest.setServiceCategoryId(1);//服务标准类目id

        StoreInfoDTO storeInfoDTO = new StoreInfoDTO();//门店信息
        storeInfoDTO.setSellerId(sellerId);//商家ID

        CustomInfo customInfo = new CustomInfo();//门店地址
        customInfo.setTownName(caiNiaoorderDomain.getStoreInfoDTOownName());//区名称
        customInfo.setAddress(caiNiaoorderDomain.getStoreInfoDTOaddress());//地址信息
        customInfo.setProvName(caiNiaoorderDomain.getStoreInfoDTOprovName());//省名称
        customInfo.setCityName(caiNiaoorderDomain.getStoreInfoDTOcityName());//市名称
        customInfo.setPhone(caiNiaoorderDomain.getStoreInfoDTOphone());//手机
        customInfo.setName(caiNiaoorderDomain.getStoreInfoDTOname());//人员姓名
        customInfo.setDetailAddress(caiNiaoorderDomain.getStoreInfoDTOdetailAddress());//详细地址

        //测试数据
        customInfo.setAreaName("无");
        customInfo.setLatitude("31.050696");
        customInfo.setLongitude("122.487506");
        //是否具备打印能力

        storeInfoDTO.setStoreAddress(customInfo);

        storeInfoDTO.setStorePhone(caiNiaoorderDomain.getStoreInfoDTOstorePhone());//门店联系方式
        storeInfoDTO.setStoreContact(caiNiaoorderDomain.getStoreInfoDTOstoreContact());//门店联系人
        storeInfoDTO.setStoreName(caiNiaoorderDomain.getStoreInfoDTOstoreName());//门店名称
        storeInfoDTO.setStoreId(Long.valueOf(caiNiaoorderDomain.getStoreId()));//门店id

        storeInfoDTO.setExpectSendTime(new Date());

        openStoreCreateOrderRequest.setStoreInfo(storeInfoDTO);


        OpenStorePackageInfoDTO openStorePackageInfoDTO = new OpenStorePackageInfoDTO();

        List<OpenStoreTradeInfoDTO> openStoreTradeInfoDTOS = new ArrayList<>();

        OpenStoreTradeInfoDTO openStoreTradeInfoDTO = new OpenStoreTradeInfoDTO();

        openStoreTradeInfoDTO.setAmount(caiNiaoorderDomain.getAmount());//订单总金额
        openStoreTradeInfoDTO.setIsMain(1);

        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<CaiNiaoorderDomainItem> caiNiaoorderDomainItems = openStoreCreateOrderService.getorderitem(yunorderid);

        if (caiNiaoorderDomainItems.isEmpty()) {

            return ResultGenerator.genFailResult("没有查询到明细");
        }

        for (int i = 0; i < caiNiaoorderDomainItems.size(); i++) {

            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName(caiNiaoorderDomainItems.get(i).getName());
            itemDTO.setCount(caiNiaoorderDomainItems.get(i).getCount());
            itemDTOS.add(itemDTO);
        }

        openStoreTradeInfoDTO.setIsMain(1);
        openStoreTradeInfoDTO.setTradeId(yunorderid);//交易ID

        openStoreTradeInfoDTO.setParentId(yunorderid);//父订单Id ,如果订单只有一件商品，那么他自己是自己的父订单，所以这种情况下parentId = tradeId,如果订单的商品多于一个，那么父订单的parentId是null，子订单的parentId就是父订单的tradeId


        openStoreTradeInfoDTO.setItemList(itemDTOS);
        openStoreTradeInfoDTO.setIsDetail(0);
        openStoreTradeInfoDTOS.add(openStoreTradeInfoDTO);

        openStorePackageInfoDTO.setTradeInfoDTOS(openStoreTradeInfoDTOS);

        openStoreCreateOrderRequest.setPackageInfoDTO(openStorePackageInfoDTO);


        CustomInfo customInforeceiver = new CustomInfo();//收货人信息
        customInforeceiver.setTownName(caiNiaoorderDomain.getTownName());//区名称
        customInforeceiver.setAddress(caiNiaoorderDomain.getAddress());//地址信息
        customInforeceiver.setProvName(caiNiaoorderDomain.getProvName());//省名称
        customInforeceiver.setCityName(caiNiaoorderDomain.getCityName());//市名称
        customInforeceiver.setPhone(caiNiaoorderDomain.getPhone());//手机
        customInforeceiver.setName(caiNiaoorderDomain.getName());//人员姓名
        customInforeceiver.setDetailAddress(caiNiaoorderDomain.getAddress());//详细地址
        customInforeceiver.setAreaName("无");


        openStoreCreateOrderRequest.setReceiverUserInfoDO(customInforeceiver);

        Log.info("菜鸟发货单RequestJSON：" + JSON.toJSONString(openStoreCreateOrderRequest));

        MerchantCreateCainiaoOrderRequest merchantCreateCainiaoOrderRequest = new MerchantCreateCainiaoOrderRequest();

        merchantCreateCainiaoOrderRequest.setOpenStoreCreateOrderRequest(openStoreCreateOrderRequest);
        System.out.println(JSON.toJSONString(merchantCreateCainiaoOrderRequest));
        MerchantCreateCainiaoOrderResponse send = pacClient.send(merchantCreateCainiaoOrderRequest, sendSysParams);
        System.out.println("Success:" + send.isSuccess());
        Log.info("菜鸟发货单ResponseJSON：" + JSON.toJSONString(send));

        if (send.isSuccess()) {//创建订单成功

            String data= send.getResult().getStoreOrderCreateResponse().getPrintData();//面单信息
            String printData = data.replace("\\","");



            //处理打印单子面单数据
            JSONObject print=new JSONObject();
            JSONObject task=new JSONObject();
            JSONArray documents=new JSONArray();
            JSONObject json=new JSONObject();
            JSONArray contents=new JSONArray();
            JSONObject jsondata=new JSONObject();

            print.put("cmd","print");
            print.put("requestID",yunorderid);
            print.put("version","1.0");

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
            task.put("taskID",yunorderid+sdf.format(date));
            task.put("preview",false);
            task.put("printer","");
            task.put("notifyMode","allInOne");
            task.put("previewType","pdf");

            json.put("documentID",send.getResult().getStoreOrderCreateResponse().getMailNo());

            jsondata.put("data",JSONObject.parseObject(printData));

            contents.add(jsondata);
            json.put("contents",contents);

            documents.add(json);
            task.put("documents",documents);


            print.put("task",task);


            String cpCode = send.getResult().getStoreOrderCreateResponse().getCpCode();//快递公司code
            String cpName = send.getResult().getStoreOrderCreateResponse().getCpName();//快递公司名称
            String orderId = send.getResult().getStoreOrderCreateResponse().getOrderId();//菜鸟订单id
            String mailNo = send.getResult().getStoreOrderCreateResponse().getMailNo();//运单号

            CaiNiaoorderendDomain caiNiaoorderendDomain = new CaiNiaoorderendDomain();
            caiNiaoorderendDomain.setPrintData(print.toString());
            caiNiaoorderendDomain.setCpCode(cpCode);
            caiNiaoorderendDomain.setCpName(cpName);
            caiNiaoorderendDomain.setOrderId(orderId);
            caiNiaoorderendDomain.setMailNo(mailNo);
            caiNiaoorderendDomain.setId(Integer.valueOf(yunorderid));
            openStoreCreateOrderService.update(caiNiaoorderendDomain);

            //String a="s";

            //int b=Integer.valueOf(a);

            List<ConsignRequest> consignRequests = new ArrayList<>();
            ConsignRequest consignRequest=new ConsignRequest();
            consignRequest.setSellerId(sellerId);//商家ID
            consignRequest.setPackageId(Long.valueOf(orderId));//包裹id
            consignRequests.add(consignRequest);
            Log.info("菜鸟配送订单发货(寻求运力)RequestJSON：" + JSON.toJSONString(consignRequest));
            MerchantSearchCourierRequest merchantSearchCourierRequest = new MerchantSearchCourierRequest();
            merchantSearchCourierRequest.setArg0(consignRequests);
            MerchantSearchCourierResponse send1 = pacClient.send(merchantSearchCourierRequest, sendSysParams);
            Log.info("菜鸟配送订单发货(寻求运力)ResponseJSON：" + JSON.toJSONString(send1));

            if (send1.isSuccess()) {//创建订单成功
                return ResultGenerator.genSuccessResult();
            } else {

                return ResultGenerator.genFailResult(send1.getErrorMsg());
            }

        } else {//创建订单失败

            return ResultGenerator.genFailResult(send.getErrorMsg());
        }


    }

    //MERCHANT_CANCEL_ORDER
    //商家取消菜鸟单
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/order.cancel", method = RequestMethod.POST)
    public @ResponseBody
    Result ordercancel(@RequestBody BosPassMeYunorder bosPassMeYunorder) {

        PacClient pacClient = new PacClient(appkey, secretKey, url);
        pacClient.getLogger().setLogEnable(false);//关闭日志打印

        SendSysParams sendSysParams = new SendSysParams();
        sendSysParams.setFromCode(token);//一定要填写自己的cpcode , ISV请求填写token值

        String yunorderid = bosPassMeYunorder.getId();//020门店发货单ID
        Log.info("发货订单ID：" + yunorderid);
        String ordercode = openStoreCreateOrderService.getordercode(yunorderid);
        Log.info("发货订单code：" + ordercode);
        OpenStoreCancelOrderRequest openStoreCancelOrderRequest = new OpenStoreCancelOrderRequest();
        openStoreCancelOrderRequest.setOrderId(Long.valueOf(ordercode));
        openStoreCancelOrderRequest.setCancelReason("商家取消");
        openStoreCancelOrderRequest.setUserId(sellerId);
        Log.info("商家取消菜鸟单RequestJSON：" + JSON.toJSONString(openStoreCancelOrderRequest));
        MerchantCancelOrderRequest merchantCancelOrderRequest = new MerchantCancelOrderRequest();
        merchantCancelOrderRequest.setOpenStoreCancelOrderRequest(openStoreCancelOrderRequest);
        MerchantCancelOrderResponse send = pacClient.send(merchantCancelOrderRequest, sendSysParams);
        Log.info("商家取消菜鸟单ResponseJSON：" + JSON.toJSONString(send));

        if (send.isSuccess()) {//成功

            return ResultGenerator.genSuccessResult();
        } else {//失败

            return ResultGenerator.genFailResult(send.getErrorCode());
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/order.test", method = RequestMethod.POST)
    public @ResponseBody
    Result test(@RequestBody BosPassMeYunorder bosPassMeYunorder) {

        System.out.println(JSON.toJSONString(openStoreCreateOrderService.getorderitem("1043075")));
        return ResultGenerator.genSuccessResult();
    }

}
