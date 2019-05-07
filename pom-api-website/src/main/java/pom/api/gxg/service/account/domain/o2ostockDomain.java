package pom.api.gxg.service.account.domain;

import java.util.List;

public class o2ostockDomain {

    public List<String> p_sku;
    public List<String> p_storecode;

    public List<String> getP_sku() {
        return p_sku;
    }

    public void setP_sku(List<String> p_sku) {
        this.p_sku = p_sku;
    }

    public List<String> getP_storecode() {
        return p_storecode;
    }

    public void setP_storecode(List<String> p_storecode) {
        this.p_storecode = p_storecode;
    }
}
