package pom.api.gxg.service.account.dao;

import pom.api.gxg.service.account.domain.CaiNiaoorderDomain;
import pom.api.gxg.service.account.domain.CaiNiaoorderDomainItem;
import pom.api.gxg.service.account.domain.CaiNiaoorderendDomain;

import java.util.List;

public interface OpenStoreCreateOrderMapper {


    CaiNiaoorderDomain getorder(String id);

    String getordercode(String id);

    int update(CaiNiaoorderendDomain order);

    List<CaiNiaoorderDomainItem> getorderitem(String id);

}
