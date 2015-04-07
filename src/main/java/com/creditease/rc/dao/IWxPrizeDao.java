package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.WxPrize;
import com.creditease.rc.vo.Prize;


public interface IWxPrizeDao {

	boolean batchInsetPrizeList(List<WxPrize> prizes);

	
}