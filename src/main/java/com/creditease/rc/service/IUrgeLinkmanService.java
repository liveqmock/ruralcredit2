package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Urge;
import com.creditease.rc.domain.UrgeLinkman;

public interface IUrgeLinkmanService {

	boolean insert(UrgeLinkman urgeLinkman);

	boolean dynamicUpdate(UrgeLinkman urgeLinkman);

	boolean insertUrgeLinkmanList(List<UrgeLinkman> urgeLinkmans);

	List<UrgeLinkman> queryUrgeLinkmanList(Long urgelinkmanId);

}
