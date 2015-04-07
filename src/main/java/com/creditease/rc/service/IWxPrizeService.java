package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxPrize;
import com.creditease.rc.vo.Prize;

public interface IWxPrizeService {

	Message batchInsetPrizeList(List<WxPrize> prizes);


}
