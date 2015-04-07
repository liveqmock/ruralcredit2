package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.UrgeLinkman;

public interface IUrgeLinkmanDao {

	boolean insert(UrgeLinkman urgeLinkman);

	boolean dynamicUpdate(UrgeLinkman urgeLinkman);

	boolean insertUrgeLinkmanList(List<UrgeLinkman> urgeLinkmans);

	List<UrgeLinkman> queryUrgeLinkmanList(Long urgelinkmanId);

}
