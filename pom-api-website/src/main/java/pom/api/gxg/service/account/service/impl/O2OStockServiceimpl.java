package pom.api.gxg.service.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pom.api.gxg.service.account.dao.O2OStockMapper;
import pom.api.gxg.service.account.entity.o2o.O2ObosStock;
import pom.api.gxg.service.account.service.O2OStockService;

@Service("O2OStockService")
@Transactional(readOnly=true)
public class O2OStockServiceimpl implements O2OStockService {

    @Autowired
    private O2OStockMapper o2OStockMapper;


    @Override
    public String getStock(O2ObosStock o2ObosStock) {

        return o2OStockMapper.getStock(o2ObosStock);
    }
}
