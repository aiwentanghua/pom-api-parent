package pom.api.gxg.service.account.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import pom.api.gxg.service.account.dao.PureUserMapper;
import pom.api.gxg.service.account.domain.PureUser;
import pom.api.gxg.service.account.service.PureUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by fz on 2018/12/20.
 */
@Service("PureUserService")
@Transactional(readOnly=true)
public class PureUserServiceImpl implements PureUserService {

	@Autowired
	private PureUserMapper pureUserMapper;

	@Override
	public PureUser selectByPrimaryKey(Integer id){
		return pureUserMapper.select(id);
	}
	
}
