package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.Urge;
import com.creditease.rc.domain.UrgeLinkman;

public class UrgeAndLinkmanVo {

	private UrgeVo urgeVo;

	private List<UrgeLinkman> urgeLinkmans;

	public List<UrgeLinkman> getUrgeLinkmans() {
		return urgeLinkmans;
	}

	public void setUrgeLinkmans(List<UrgeLinkman> urgeLinkmans) {
		this.urgeLinkmans = urgeLinkmans;
	}

	public UrgeVo getUrgeVo() {
		return urgeVo;
	}

	public void setUrgeVo(UrgeVo urgeVo) {
		this.urgeVo = urgeVo;
	}

}
