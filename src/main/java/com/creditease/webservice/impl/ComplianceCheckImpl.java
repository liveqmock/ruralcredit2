package com.creditease.webservice.impl;

import com.creditease.rc.service.IComCheService;
import com.creditease.rc.vo.ComplianceCheckReq;
import com.creditease.rc.vo.ComplianceCheckRes;
import com.creditease.webservice.IComplianceCheckWS;

import javax.annotation.Resource;

/**
 * Created by v-weizhang on 2014-12-16.
 */
public class ComplianceCheckImpl implements IComplianceCheckWS {
    @Resource
    private IComCheService comCheService;

    @Override
    public ComplianceCheckRes searchComCheByType(ComplianceCheckReq req) {
        return comCheService.searchComCheByType(req);
    }


    @Override
    public ComplianceCheckRes saveComCheByType(ComplianceCheckReq req) {
        return comCheService.saveComCheByType(req);
    }
}
