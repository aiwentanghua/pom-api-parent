package pom.api.gxg.service.account.entity.o2o;

/**
 * Copyright 2019 bejson.com
 */

import java.util.List;

/**
 * Auto-generated: 2019-01-15 16:53:52
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class OraclePassMeo2oorder {

    /**
     * 订单编号（外部系统对接此字段无效）
     */
    private String order_sn;

    /**
     * 是否礼品（0:正常商品;1:礼品;）1:正常，2，退货，3，礼品，4.正常
     * 如果是3传1：礼品，其他都传0
     */
    private List<String> type;
    /**
     * * 收货人的手机号码（电话号码和手机号码两个必须有一个不能为空）
     */
    private String RECEIVER_MOBILE;

    /**
     * 应付金额/已付金额
     */
    private List<Float> paymount;

    /**
     * 商品吊牌价
     */
    private List<Float> pricelist;

    /**
     * 收货人的省份
     */
    private String C_PROVINCE_ID__NAME;
    /**
     * 订单下单时间（YYYY-MM-DD HH:II:SS）
     */
    private String createtime;
    /**
     * 收货人的电话号码
     */
    private String RECEIVER_PHONE;
    /**
     * 收货人的邮政编码
     */
    private String RECEIVER_ZIP;

    /**
     * 店铺代码
     */
    private String storecode;
    /**
     * 数量
     */
    private List<Integer> qty;

    /**
     * SKU
     */
    private List<String> productno;

    /**
     * 收货人姓名
     */
    private String RECEIVER_NAME;

    /**
     * 收货人的详细地址
     */
    private String RECEIVER_ADDRESS;
    /**
     * 收货人城市
     */
    private String C_CITY_ID__NAME;

    /**
     * 收货人市区
     */
    private String C_DISTRICT_ID__NAME;

    /**
     * 商品售价/商品实际成交价
     */
    private List<Float> priceactual;
    /**
     * pay_time
     */
    private String cashtime;

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getOrder_sn() {
        return order_sn;
    }



    /*public void setMastercodepdts(String mastercodepdts) {
        this.mastercodepdts = mastercodepdts;
    }
    public String getMastercodepdts() {
        return mastercodepdts;
    }

    public void setC_PROVINCE_ID(String C_PROVINCE_ID) {
        this.C_PROVINCE_ID = C_PROVINCE_ID;
    }
    public String getC_PROVINCE_ID() {
        return C_PROVINCE_ID;
    }

    public void setTdefposdis_name(String tdefposdis_name) {
        this.tdefposdis_name = tdefposdis_name;
    }
    public String getTdefposdis_name() {
        return tdefposdis_name;
    }

    public void setVip_amt(int vip_amt) {
        this.vip_amt = vip_amt;
    }
    public int getVip_amt() {
        return vip_amt;
    }

    public void setDiscount(List<Integer> discount) {
        this.discount = discount;
    }
    public List<Integer> getDiscount() {
        return discount;
    }

    public void setM_retail_linenos(List<Integer> m_retail_linenos) {
        this.m_retail_linenos = m_retail_linenos;
    }
    public List<Integer> getM_retail_linenos() {
        return m_retail_linenos;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
    public String getStorename() {
        return storename;
    }

    public void setM_retail_useintegral(List<String> m_retail_useintegral) {
        this.m_retail_useintegral = m_retail_useintegral;
    }
    public List<String> getM_retail_useintegral() {
        return m_retail_useintegral;
    }*/

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getType() {
        return type;
    }

    /*public void setStoreid(int storeid) {
        this.storeid = storeid;
    }
    public int getStoreid() {
        return storeid;
    }*/

    public void setRECEIVER_MOBILE(String RECEIVER_MOBILE) {
        this.RECEIVER_MOBILE = RECEIVER_MOBILE;
    }

    public String getRECEIVER_MOBILE() {
        return RECEIVER_MOBILE;
    }

    /*public void setIS_ADDVIPADDRESS(String IS_ADDVIPADDRESS) {
        this.IS_ADDVIPADDRESS = IS_ADDVIPADDRESS;
    }
    public String getIS_ADDVIPADDRESS() {
        return IS_ADDVIPADDRESS;
    }

    public void setPlatform_discount_amts(List<String> platform_discount_amts) {
        this.platform_discount_amts = platform_discount_amts;
    }
    public List<String> getPlatform_discount_amts() {
        return platform_discount_amts;
    }

    public void setPrice(List<Integer> price) {
        this.price = price;
    }
    public List<Integer> getPrice() {
        return price;
    }

    public void setMultisalerRates(List<String> multisalerRates) {
        this.multisalerRates = multisalerRates;
    }
    public List<String> getMultisalerRates() {
        return multisalerRates;
    }

    public void setMarkcardid(int markcardid) {
        this.markcardid = markcardid;
    }
    public int getMarkcardid() {
        return markcardid;
    }

    public void setIscloud(String iscloud) {
        this.iscloud = iscloud;
    }
    public String getIscloud() {
        return iscloud;
    }

    public void setCom_disnames(List<String> com_disnames) {
        this.com_disnames = com_disnames;
    }
    public List<String> getCom_disnames() {
        return com_disnames;
    }

    public void setAlipayMinus(int alipayMinus) {
        this.alipayMinus = alipayMinus;
    }
    public int getAlipayMinus() {
        return alipayMinus;
    }

    public void setCouponVouNos(String couponVouNos) {
        this.couponVouNos = couponVouNos;
    }
    public String getCouponVouNos() {
        return couponVouNos;
    }

    public void setCouponVouNosDisAmt(String couponVouNosDisAmt) {
        this.couponVouNosDisAmt = couponVouNosDisAmt;
    }
    public String getCouponVouNosDisAmt() {
        return couponVouNosDisAmt;
    }*/

    public void setPaymount(List<Float> paymount) {
        this.paymount = paymount;
    }

    public List<Float> getPaymount() {
        return paymount;
    }

    /*public void setPay_currencymount(List<Integer> pay_currencymount) {
        this.pay_currencymount = pay_currencymount;
    }
    public List<Integer> getPay_currencymount() {
        return pay_currencymount;
    }

    public void setTdefposdis_names(String tdefposdis_names) {
        this.tdefposdis_names = tdefposdis_names;
    }
    public String getTdefposdis_names() {
        return tdefposdis_names;
    }

    public void setTdefposdis_id(int tdefposdis_id) {
        this.tdefposdis_id = tdefposdis_id;
    }
    public int getTdefposdis_id() {
        return tdefposdis_id;
    }

    public void setCommon01(String common01) {
        this.common01 = common01;
    }
    public String getCommon01() {
        return common01;
    }

    public void setSeleStayType(int seleStayType) {
        this.seleStayType = seleStayType;
    }
    public int getSeleStayType() {
        return seleStayType;
    }*/

    public void setPricelist(List<Float> pricelist) {
        this.pricelist = pricelist;
    }

    public List<Float> getPricelist() {
        return pricelist;
    }

    /*public void setCommon02(String common02) {
        this.common02 = common02;
    }
    public String getCommon02() {
        return common02;
    }

    public void setM_retail_vouinfoes(List<String> m_retail_vouinfoes) {
        this.m_retail_vouinfoes = m_retail_vouinfoes;
    }
    public List<String> getM_retail_vouinfoes() {
        return m_retail_vouinfoes;
    }

    public void setCommon03(String common03) {
        this.common03 = common03;
    }
    public String getCommon03() {
        return common03;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }
    public List<String> getSize() {
        return size;
    }

    public void setOrgdocno(List<String> orgdocno) {
        this.orgdocno = orgdocno;
    }
    public List<String> getOrgdocno() {
        return orgdocno;
    }

    public void setM_retail_vipbirdis_id(List<String> m_retail_vipbirdis_id) {
        this.m_retail_vipbirdis_id = m_retail_vipbirdis_id;
    }
    public List<String> getM_retail_vipbirdis_id() {
        return m_retail_vipbirdis_id;
    }

    public void setIsintl(String isintl) {
        this.isintl = isintl;
    }
    public String getIsintl() {
        return isintl;
    }

    public void setIsPendingOrder(String isPendingOrder) {
        this.isPendingOrder = isPendingOrder;
    }
    public String getIsPendingOrder() {
        return isPendingOrder;
    }

    public void setBilldate(long billdate) {
        this.billdate = billdate;
    }
    public long getBilldate() {
        return billdate;
    }

    public void setPrintmetrail(String printmetrail) {
        this.printmetrail = printmetrail;
    }
    public String getPrintmetrail() {
        return printmetrail;
    }

    public void setM_retail_deliverydate(List<String> m_retail_deliverydate) {
        this.m_retail_deliverydate = m_retail_deliverydate;
    }
    public List<String> getM_retail_deliverydate() {
        return m_retail_deliverydate;
    }

    public void setIsexchange(List<String> isexchange) {
        this.isexchange = isexchange;
    }
    public List<String> getIsexchange() {
        return isexchange;
    }

    public void setPay_currencydescriptions(List<String> pay_currencydescriptions) {
        this.pay_currencydescriptions = pay_currencydescriptions;
    }
    public List<String> getPay_currencydescriptions() {
        return pay_currencydescriptions;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }
    public String getOperatorname() {
        return operatorname;
    }

    public void setM_retail_markbaltype_id(List<Integer> m_retail_markbaltype_id) {
        this.m_retail_markbaltype_id = m_retail_markbaltype_id;
    }
    public List<Integer> getM_retail_markbaltype_id() {
        return m_retail_markbaltype_id;
    }

    public void setDealtime(String dealtime) {
        this.dealtime = dealtime;
    }
    public String getDealtime() {
        return dealtime;
    }

    public void setIS_MSCODE(List<String> IS_MSCODE) {
        this.IS_MSCODE = IS_MSCODE;
    }
    public List<String> getIS_MSCODE() {
        return IS_MSCODE;
    }

    public void setMultisalerids(List<String> multisalerids) {
        this.multisalerids = multisalerids;
    }
    public List<String> getMultisalerids() {
        return multisalerids;
    }

    public void setIS_BPOS_CLOUD(String IS_BPOS_CLOUD) {
        this.IS_BPOS_CLOUD = IS_BPOS_CLOUD;
    }
    public String getIS_BPOS_CLOUD() {
        return IS_BPOS_CLOUD;
    }

    public void setPay_currencysigns(List<String> pay_currencysigns) {
        this.pay_currencysigns = pay_currencysigns;
    }
    public List<String> getPay_currencysigns() {
        return pay_currencysigns;
    }

    public void setDIS_ALL(int DIS_ALL) {
        this.DIS_ALL = DIS_ALL;
    }
    public int getDIS_ALL() {
        return DIS_ALL;
    }

    public void setVIRTUALPRICE(List<Integer> VIRTUALPRICE) {
        this.VIRTUALPRICE = VIRTUALPRICE;
    }
    public List<Integer> getVIRTUALPRICE() {
        return VIRTUALPRICE;
    }

    public void setVipid(int vipid) {
        this.vipid = vipid;
    }
    public int getVipid() {
        return vipid;
    }

    public void setMultisalers(List<List<String>> multisalers) {
        this.multisalers = multisalers;
    }
    public List<List<String>> getMultisalers() {
        return multisalers;
    }

    public void setVipbirdisid(int vipbirdisid) {
        this.vipbirdisid = vipbirdisid;
    }
    public int getVipbirdisid() {
        return vipbirdisid;
    }

    public void setHAS_BRITHDAYDIS(String HAS_BRITHDAYDIS) {
        this.HAS_BRITHDAYDIS = HAS_BRITHDAYDIS;
    }
    public String getHAS_BRITHDAYDIS() {
        return HAS_BRITHDAYDIS;
    }

    public void setM_stay_id(int m_stay_id) {
        this.m_stay_id = m_stay_id;
    }
    public int getM_stay_id() {
        return m_stay_id;
    }

    public void setSin_disids(List<Integer> sin_disids) {
        this.sin_disids = sin_disids;
    }
    public List<Integer> getSin_disids() {
        return sin_disids;
    }

    public void setC_DISTRICT_ID(String C_DISTRICT_ID) {
        this.C_DISTRICT_ID = C_DISTRICT_ID;
    }
    public String getC_DISTRICT_ID() {
        return C_DISTRICT_ID;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
    public int getIntegral() {
        return integral;
    }

    public void setPaywayid(List<Integer> paywayid) {
        this.paywayid = paywayid;
    }
    public List<Integer> getPaywayid() {
        return paywayid;
    }*/

    public void setC_PROVINCE_ID__NAME(String C_PROVINCE_ID__NAME) {
        this.C_PROVINCE_ID__NAME = C_PROVINCE_ID__NAME;
    }

    public String getC_PROVINCE_ID__NAME() {
        return C_PROVINCE_ID__NAME;
    }

    /*public void setAdd_disids(List<Integer> add_disids) {
        this.add_disids = add_disids;
    }
    public List<Integer> getAdd_disids() {
        return add_disids;
    }

    public void setDiscription_pay(List<String> discription_pay) {
        this.discription_pay = discription_pay;
    }
    public List<String> getDiscription_pay() {
        return discription_pay;
    }

    public void setRememberSelectedWeatherValue(String rememberSelectedWeatherValue) {
        this.rememberSelectedWeatherValue = rememberSelectedWeatherValue;
    }
    public String getRememberSelectedWeatherValue() {
        return rememberSelectedWeatherValue;
    }

    public void setVipDisName(String vipDisName) {
        this.vipDisName = vipDisName;
    }
    public String getVipDisName() {
        return vipDisName;
    }*/

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    /*public void setC_CITY_ID(String C_CITY_ID) {
        this.C_CITY_ID = C_CITY_ID;
    }
    public String getC_CITY_ID() {
        return C_CITY_ID;
    }

    public void setM_retail_handdis(List<String> m_retail_handdis) {
        this.m_retail_handdis = m_retail_handdis;
    }
    public List<String> getM_retail_handdis() {
        return m_retail_handdis;
    }

    public void setVouinfoes(List<String> vouinfoes) {
        this.vouinfoes = vouinfoes;
    }
    public List<String> getVouinfoes() {
        return vouinfoes;
    }

    public void setSin_disnames(List<String> sin_disnames) {
        this.sin_disnames = sin_disnames;
    }
    public List<String> getSin_disnames() {
        return sin_disnames;
    }

    public void setACTUALPRICE(List<Integer> ACTUALPRICE) {
        this.ACTUALPRICE = ACTUALPRICE;
    }
    public List<Integer> getACTUALPRICE() {
        return ACTUALPRICE;
    }

    public void setPay_currencyid(List<Integer> pay_currencyid) {
        this.pay_currencyid = pay_currencyid;
    }
    public List<Integer> getPay_currencyid() {
        return pay_currencyid;
    }

    public void setC_REALSTORE(String C_REALSTORE) {
        this.C_REALSTORE = C_REALSTORE;
    }
    public String getC_REALSTORE() {
        return C_REALSTORE;
    }

    public void setBuss_discount_amts(List<String> buss_discount_amts) {
        this.buss_discount_amts = buss_discount_amts;
    }
    public List<String> getBuss_discount_amts() {
        return buss_discount_amts;
    }

    public void setSalerid(int salerid) {
        this.salerid = salerid;
    }
    public int getSalerid() {
        return salerid;
    }

    public void setDisranges(List<Integer> disranges) {
        this.disranges = disranges;
    }
    public List<Integer> getDisranges() {
        return disranges;
    }

    public void setItem_integrals(List<Integer> item_integrals) {
        this.item_integrals = item_integrals;
    }
    public List<Integer> getItem_integrals() {
        return item_integrals;
    }

    public void setC_integralmin_id(int c_integralmin_id) {
        this.c_integralmin_id = c_integralmin_id;
    }
    public int getC_integralmin_id() {
        return c_integralmin_id;
    }

    public void setRealpaymount(List<Integer> realpaymount) {
        this.realpaymount = realpaymount;
    }
    public List<Integer> getRealpaymount() {
        return realpaymount;
    }

    public void setIsaddvip(String isaddvip) {
        this.isaddvip = isaddvip;
    }
    public String getIsaddvip() {
        return isaddvip;
    }*/

    public void setRECEIVER_PHONE(String RECEIVER_PHONE) {
        this.RECEIVER_PHONE = RECEIVER_PHONE;
    }

    public String getRECEIVER_PHONE() {
        return RECEIVER_PHONE;
    }

    /*public void setAUTHORIZATION(String AUTHORIZATION) {
        this.AUTHORIZATION = AUTHORIZATION;
    }
    public String getAUTHORIZATION() {
        return AUTHORIZATION;
    }*/

    public void setRECEIVER_ZIP(String RECEIVER_ZIP) {
        this.RECEIVER_ZIP = RECEIVER_ZIP;
    }

    public String getRECEIVER_ZIP() {
        return RECEIVER_ZIP;
    }

    /*public void setAmtchange(int amtchange) {
        this.amtchange = amtchange;
    }
    public int getAmtchange() {
        return amtchange;
    }

    public void setSys_seqs(List<String> sys_seqs) {
        this.sys_seqs = sys_seqs;
    }
    public List<String> getSys_seqs() {
        return sys_seqs;
    }

    public void setC_INVENTORY(String C_INVENTORY) {
        this.C_INVENTORY = C_INVENTORY;
    }
    public String getC_INVENTORY() {
        return C_INVENTORY;
    }

    public void setMarketno_code(String marketno_code) {
        this.marketno_code = marketno_code;
    }
    public String getMarketno_code() {
        return marketno_code;
    }

    public void setSalercode(List<String> salercode) {
        this.salercode = salercode;
    }
    public List<String> getSalercode() {
        return salercode;
    }

    public void setADDRESS_DESC(String ADDRESS_DESC) {
        this.ADDRESS_DESC = ADDRESS_DESC;
    }
    public String getADDRESS_DESC() {
        return ADDRESS_DESC;
    }

    public void setSaler(List<String> saler) {
        this.saler = saler;
    }
    public List<String> getSaler() {
        return saler;
    }

    public void setMallcode(List<String> mallcode) {
        this.mallcode = mallcode;
    }
    public List<String> getMallcode() {
        return mallcode;
    }

    public void setM_retail_price_withouttax(List<String> m_retail_price_withouttax) {
        this.m_retail_price_withouttax = m_retail_price_withouttax;
    }
    public List<String> getM_retail_price_withouttax() {
        return m_retail_price_withouttax;
    }

    public void setM_retail_displaymodes(List<String> m_retail_displaymodes) {
        this.m_retail_displaymodes = m_retail_displaymodes;
    }
    public List<String> getM_retail_displaymodes() {
        return m_retail_displaymodes;
    }

    public void setDIS_BEFOR(String DIS_BEFOR) {
        this.DIS_BEFOR = DIS_BEFOR;
    }
    public String getDIS_BEFOR() {
        return DIS_BEFOR;
    }

    public void setDistypes(List<String> distypes) {
        this.distypes = distypes;
    }
    public List<String> getDistypes() {
        return distypes;
    }

    public void setM_retail_displayarea_id(List<Integer> m_retail_displayarea_id) {
        this.m_retail_displayarea_id = m_retail_displayarea_id;
    }
    public List<Integer> getM_retail_displayarea_id() {
        return m_retail_displayarea_id;
    }

    public void setIstaobao(String istaobao) {
        this.istaobao = istaobao;
    }
    public String getIstaobao() {
        return istaobao;
    }

    public void setM_retail_retreason_id(List<Integer> m_retail_retreason_id) {
        this.m_retail_retreason_id = m_retail_retreason_id;
    }
    public List<Integer> getM_retail_retreason_id() {
        return m_retail_retreason_id;
    }

    public void setRememberSelectedRetailValue(String rememberSelectedRetailValue) {
        this.rememberSelectedRetailValue = rememberSelectedRetailValue;
    }
    public String getRememberSelectedRetailValue() {
        return rememberSelectedRetailValue;
    }

    public void setIsHold(boolean isHold) {
        this.isHold = isHold;
    }
    public boolean getIsHold() {
        return isHold;
    }
    public void setReceiptids(List<Integer> receiptids) {
        this.receiptids = receiptids;
    }
    public List<Integer> getReceiptids() {
        return receiptids;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    public String getCardno() {
        return cardno;
    }*/

    public void setStorecode(String storecode) {
        this.storecode = storecode;
    }

    public String getStorecode() {
        return storecode;
    }

    /*public void setC_REALNAME(String C_REALNAME) {
        this.C_REALNAME = C_REALNAME;
    }
    public String getC_REALNAME() {
        return C_REALNAME;
    }*/

    public void setQty(List<Integer> qty) {
        this.qty = qty;
    }

    public List<Integer> getQty() {
        return qty;
    }

    /*public void setDiscription2(List<String> discription2) {
        this.discription2 = discription2;
    }
    public List<String> getDiscription2() {
        return discription2;
    }

    public void setM_retail_taxrate(List<String> m_retail_taxrate) {
        this.m_retail_taxrate = m_retail_taxrate;
    }
    public List<String> getM_retail_taxrate() {
        return m_retail_taxrate;
    }*/

    public void setProductno(List<String> productno) {
        this.productno = productno;
    }

    public List<String> getProductno() {
        return productno;
    }

    /*public void setUpload_type(String upload_type) {
        this.upload_type = upload_type;
    }
    public String getUpload_type() {
        return upload_type;
    }

    public void setPosnum(String posnum) {
        this.posnum = posnum;
    }
    public String getPosnum() {
        return posnum;
    }

    public void setBpos_vip(OraclePassMeo2oorderBpos_vip bpos_vip) {
        this.bpos_vip = bpos_vip;
    }
    public OraclePassMeo2oorderBpos_vip getBpos_vip() {
        return bpos_vip;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }
    public String getPtype() {
        return ptype;
    }

    public void setOff_percents(List<String> off_percents) {
        this.off_percents = off_percents;
    }
    public List<String> getOff_percents() {
        return off_percents;
    }

    public void setCanModify(int canModify) {
        this.canModify = canModify;
    }
    public int getCanModify() {
        return canModify;
    }

    public void setDescription1(List<String> description1) {
        this.description1 = description1;
    }
    public List<String> getDescription1() {
        return description1;
    }

    public void setAdd_disnames(List<String> add_disnames) {
        this.add_disnames = add_disnames;
    }
    public List<String> getAdd_disnames() {
        return add_disnames;
    }

    public void setM_retail_picoupondis(List<Integer> m_retail_picoupondis) {
        this.m_retail_picoupondis = m_retail_picoupondis;
    }
    public List<Integer> getM_retail_picoupondis() {
        return m_retail_picoupondis;
    }*/

    public void setRECEIVER_NAME(String RECEIVER_NAME) {
        this.RECEIVER_NAME = RECEIVER_NAME;
    }

    public String getRECEIVER_NAME() {
        return RECEIVER_NAME;
    }

    public void setRECEIVER_ADDRESS(String RECEIVER_ADDRESS) {
        this.RECEIVER_ADDRESS = RECEIVER_ADDRESS;
    }

    public String getRECEIVER_ADDRESS() {
        return RECEIVER_ADDRESS;
    }

    /*public void setM_retail_org_item_id(int m_retail_org_item_id) {
        this.m_retail_org_item_id = m_retail_org_item_id;
    }
    public int getM_retail_org_item_id() {
        return m_retail_org_item_id;
    }*/

    public void setC_CITY_ID__NAME(String C_CITY_ID__NAME) {
        this.C_CITY_ID__NAME = C_CITY_ID__NAME;
    }

    public String getC_CITY_ID__NAME() {
        return C_CITY_ID__NAME;
    }

    /*public void setRefno(String refno) {
        this.refno = refno;
    }
    public String getRefno() {
        return refno;
    }

    public void setVipIntegralDis(String vipIntegralDis) {
        this.vipIntegralDis = vipIntegralDis;
    }
    public String getVipIntegralDis() {
        return vipIntegralDis;
    }

    public void setMdim11(List<String> mdim11) {
        this.mdim11 = mdim11;
    }
    public List<String> getMdim11() {
        return mdim11;
    }

    public void setM_retail_vipbirdisitemid(List<String> m_retail_vipbirdisitemid) {
        this.m_retail_vipbirdisitemid = m_retail_vipbirdisitemid;
    }
    public List<String> getM_retail_vipbirdisitemid() {
        return m_retail_vipbirdisitemid;
    }

    public void setCom_disids(List<Integer> com_disids) {
        this.com_disids = com_disids;
    }
    public List<Integer> getCom_disids() {
        return com_disids;
    }

    public void setMultisalerTrueNames(List<String> multisalerTrueNames) {
        this.multisalerTrueNames = multisalerTrueNames;
    }
    public List<String> getMultisalerTrueNames() {
        return multisalerTrueNames;
    }

    public void setNUMEB(List<Integer> NUMEB) {
        this.NUMEB = NUMEB;
    }
    public List<Integer> getNUMEB() {
        return NUMEB;
    }

    public void setTdefposdis_ids(String tdefposdis_ids) {
        this.tdefposdis_ids = tdefposdis_ids;
    }
    public String getTdefposdis_ids() {
        return tdefposdis_ids;
    }

    public void setModifyamtreason_id(List<Integer> modifyamtreason_id) {
        this.modifyamtreason_id = modifyamtreason_id;
    }
    public List<Integer> getModifyamtreason_id() {
        return modifyamtreason_id;
    }

    public void setVipDisContent(int vipDisContent) {
        this.vipDisContent = vipDisContent;
    }
    public int getVipDisContent() {
        return vipDisContent;
    }

    public void setSalercurrentid(String salercurrentid) {
        this.salercurrentid = salercurrentid;
    }
    public String getSalercurrentid() {
        return salercurrentid;
    }*/

    public void setC_DISTRICT_ID__NAME(String C_DISTRICT_ID__NAME) {
        this.C_DISTRICT_ID__NAME = C_DISTRICT_ID__NAME;
    }

    public String getC_DISTRICT_ID__NAME() {
        return C_DISTRICT_ID__NAME;
    }

    /*public void setSalerid1(List<Integer> salerid1) {
        this.salerid1 = salerid1;
    }
    public List<Integer> getSalerid1() {
        return salerid1;
    }*/

    public void setPriceactual(List<Float> priceactual) {
        this.priceactual = priceactual;
    }

    public List<Float> getPriceactual() {
        return priceactual;
    }

    /*public void setLocationtype(List<Integer> locationtype) {
        this.locationtype = locationtype;
    }
    public List<Integer> getLocationtype() {
        return locationtype;
    }

    public void setPos_seqs(List<String> pos_seqs) {
        this.pos_seqs = pos_seqs;
    }
    public List<String> getPos_seqs() {
        return pos_seqs;
    }*/

    public void setCashtime(String cashtime) {
        this.cashtime = cashtime;
    }

    public String getCashtime() {
        return cashtime;
    }

    /*public void setOperatorid(int operatorid) {
        this.operatorid = operatorid;
    }
    public int getOperatorid() {
        return operatorid;
    }

    public void setPricefastno(String pricefastno) {
        this.pricefastno = pricefastno;
    }
    public String getPricefastno() {
        return pricefastno;
    }*/

}
