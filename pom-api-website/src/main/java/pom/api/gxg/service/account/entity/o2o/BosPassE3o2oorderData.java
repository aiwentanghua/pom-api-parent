package pom.api.gxg.service.account.entity.o2o;

import java.util.List;

public class BosPassE3o2oorderData {

    private String order_sn;	//订单编号（外部系统对接此字段无效）	string
    //private String deal_code;	//平台交易号（外部系统对接此字段为准）	string
    private String sd_code;	//店铺代码	string
    //private String pay_code;	//支付方式代码	string
    //private String shipping_code;	//快递方式代码	string
    //private String user_name;	//会员名称	string
    private String receiver_name;	//收货人姓名	string
    //private String receiver_country;	//收货人的国家	string
    private String receiver_province;	//收货人的省份	string
    private String receiver_city;	//收货人的城市	string
    private String receiver_district;	//收货人的地区	string
    private String receiver_address;	//收货人的详细地址	string
    private String receiver_zip;	//收货人的邮政编码	string
    private String receiver_tel;	//收货人的电话号码	string
    //private String receiver_mobile;//	收货人的手机号码（电话号码和手机号码两个必须有一个不能为空）	string
    //private String receiver_email;	//收货人的电子邮件	string
    //private int fh_priority;	//发货优先级（0-9越大优先级越高）	int
    private String add_time;	//订单下单时间（YYYY-MM-DD HH:II:SS）	date
    private String pay_time;	//订单支付时间（YYYY-MM-DD HH:II:SS）	date
    private int pay_status;	//付款状态（0:未付款;1:已付款）默认给0	int
    //private float shipping_fee;	//运费	float
    private float order_amount;	//应付金额	float
    private float payment;	//已付金额	float
    //private int is_cod;	//交易类型 0:款到发货;1:货到付款 	int
    //private String source;	//订单来源（API:默认;TM_CLOUD:聚石塔;JD_CLOUD:云鼎;:;）	string
    //private String sub_source;	//订单子来源（TM_CLOUD1:聚石塔1; TM_CLOUD2:聚石塔2;:;:;）	string
    private List<BosPassE3o2oorderitems> items;

    public void setItems(List<BosPassE3o2oorderitems> items) {
        this.items = items;
    }

    public List<BosPassE3o2oorderitems> getItems() {
        return items;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public void setSd_code(String sd_code) {
        this.sd_code = sd_code;
    }

    /*public void setUser_name(String user_name) {
        this.user_name = user_name;
    }*/

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public void setReceiver_province(String receiver_province) {
        this.receiver_province = receiver_province;
    }

    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }

    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public void setReceiver_zip(String receiver_zip) {
        this.receiver_zip = receiver_zip;
    }

    public void setReceiver_tel(String receiver_tel) {
        this.receiver_tel = receiver_tel;
    }

    /*public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }*/

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public void setOrder_amount(float order_amount) {
        this.order_amount = order_amount;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public String getSd_code() {
        return sd_code;
    }

   /* public String getUser_name() {
        return user_name;
    }*/

    public String getReceiver_name() {
        return receiver_name;
    }

    public String getReceiver_province() {
        return receiver_province;
    }

    public String getReceiver_city() {
        return receiver_city;
    }

    public String getReceiver_district() {
        return receiver_district;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public String getReceiver_zip() {
        return receiver_zip;
    }

    public String getReceiver_tel() {
        return receiver_tel;
    }

    /*public String getReceiver_mobile() {
        return receiver_mobile;
    }*/

    public String getAdd_time() {
        return add_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public int getPay_status() {
        return pay_status;
    }

    public float getOrder_amount() {
        return order_amount;
    }

    public float getPayment() {
        return payment;
    }
}