package pom.api.gxg.service.account.dao;

import java.util.List;


import pom.api.gxg.service.account.domain.PureUser;

public interface PureUserMapper {
    int insert(PureUser record);

    PureUser select(Integer id);

    List<PureUser> selectAll();
}