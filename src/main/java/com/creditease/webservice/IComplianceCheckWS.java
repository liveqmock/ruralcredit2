package com.creditease.webservice;

import com.creditease.rc.vo.ComplianceCheckReq;
import com.creditease.rc.vo.ComplianceCheckRes;

import javax.jws.WebService;

/**
 * Created by v-weizhang on 2014-12-16.
 */
@WebService
public interface IComplianceCheckWS {

    /*
    查询合规检查 默认客服检查
     */
    public ComplianceCheckRes searchComCheByType(ComplianceCheckReq req);

    /*
    保存合规检查 默认客服检查
     */
    public ComplianceCheckRes saveComCheByType(ComplianceCheckReq req);
}
