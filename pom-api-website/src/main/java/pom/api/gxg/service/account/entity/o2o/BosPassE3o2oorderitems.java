package pom.api.gxg.service.account.entity.o2o;

public class BosPassE3o2oorderitems {
    private String order_sn;//	订单编号（外部系统对接此字段无效）	string
    //private String deal_code;//	平台交易号（外部系统对接此字段为准）	string
    private String sku;//	商品SKU编码	string
    //private String goods_sn;//	商品货号	string
    //private String goods_name;//	商品名称	string
    //private String  color_code;//	颜色代码	string
    //private String size_code;//	尺码代码	string
    private float market_price;//	商品吊牌价	float
    private float shop_price;//	商品售价	float
    private float goods_price;//	商品实际成交价	float
    private int goods_number;//	数量	int
    private int is_gift;//	是否礼品（0:正常商品;1:礼品;）1:正常，2，退货，3，礼品，4.正常	int

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setMarket_price(float market_price) {
        this.market_price = market_price;
    }

    public void setShop_price(float shop_price) {
        this.shop_price = shop_price;
    }

    public void setGoods_price(float goods_price) {
        this.goods_price = goods_price;
    }

    public void setGoods_number(int goods_number) {
        this.goods_number = goods_number;
    }

    public void setIs_gift(int is_gift) {
        this.is_gift = is_gift;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public String getSku() {
        return sku;
    }

    public float getMarket_price() {
        return market_price;
    }

    public float getShop_price() {
        return shop_price;
    }

    public float getGoods_price() {
        return goods_price;
    }

    public int getGoods_number() {
        return goods_number;
    }

    public int getIs_gift() {
        return is_gift;
    }
}