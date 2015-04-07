package com.creditease.rc.vo;

import java.util.List;

public class DataDictionaryResponseParam extends WebServiceMessage{

    List<DataDictionaryVo> voList; //返回客户端数据对象

    public List<DataDictionaryVo> getVoList() {
        return voList;
    }

    public void setVoList(List<DataDictionaryVo> voList) {
        this.voList = voList;
    }
}
