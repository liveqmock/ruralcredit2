package com.creditease.rc.vo;

import com.creditease.rc.domain.NationalStandardCode;

import java.util.List;

public class PCAResponseParam extends WebServiceMessage{

    private List<NationalStandardCode> codeList;

    public List<NationalStandardCode> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<NationalStandardCode> codeList) {
        this.codeList = codeList;
    }
}
