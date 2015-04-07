package com.creditease.rc.service;

import com.creditease.rc.vo.ComplianceCheckReq;
import com.creditease.rc.vo.ComplianceCheckRes;

/**
 * Created by v-weizhang on 2014-12-17.
 */
public interface IComCheService {

    public ComplianceCheckRes searchComCheByType(ComplianceCheckReq req);

    public ComplianceCheckRes saveComCheByType(ComplianceCheckReq req);
}
