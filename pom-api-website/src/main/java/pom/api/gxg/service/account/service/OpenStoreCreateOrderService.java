package pom.api.gxg.service.account.service;

import pom.api.gxg.service.account.domain.CaiNiaoorderDomain;
import pom.api.gxg.service.account.domain.CaiNiaoorderDomainItem;
import pom.api.gxg.service.account.domain.CaiNiaoorderendDomain;

import java.util.List;

public interface OpenStoreCreateOrderService {


    CaiNiaoorderDomain getorder(String id);

    int update(CaiNiaoorderendDomain order);

    String getordercode(String id);

    List<CaiNiaoorderDomainItem> getorderitem(String id);
}
