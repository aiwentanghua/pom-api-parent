package pom.api.gxg.service.account.service;
import pom.api.gxg.service.account.domain.PureUser;

import java.util.List;

/**
 * Created by fz on 2018/12/20.
 */
public interface PureUserService {

	PureUser selectByPrimaryKey(Integer id);
	
}
