package pom.api.gxg.service.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pom.api.gxg.service.account.dao.OpenStoreCreateOrderMapper;
import pom.api.gxg.service.account.domain.CaiNiaoorderDomain;
import pom.api.gxg.service.account.domain.CaiNiaoorderDomainItem;
import pom.api.gxg.service.account.domain.CaiNiaoorderendDomain;
import pom.api.gxg.service.account.service.OpenStoreCreateOrderService;

import java.util.List;

@Service("OpenStoreCreateOrderService")
@Transactional(readOnly = true)
public class OpenStoreCreateOrderServiceimpl implements OpenStoreCreateOrderService {
    @Autowired
    private OpenStoreCreateOrderMapper openStoreCreateOrderMapper;

    @Override
    public CaiNiaoorderDomain getorder(String id) {
        return openStoreCreateOrderMapper.getorder(id);
    }

    @Override
    public int update(CaiNiaoorderendDomain order) {
        return openStoreCreateOrderMapper.update(order);
    }

    @Override
    public String getordercode(String id) {
        return openStoreCreateOrderMapper.getordercode(id);
    }

    @Override
    public List<CaiNiaoorderDomainItem> getorderitem(String id) {
        return openStoreCreateOrderMapper.getorderitem(id);
    }


}
