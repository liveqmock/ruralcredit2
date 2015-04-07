package com.creditease.rc.vo;

import java.util.List;

/**
 * Created by v-weizhang on 2014-12-16.
 */
public class ComplianceCheckRes {
    /*
    响应码
        0 - 查询失败
        1 - 查询成功
     */
    private String code;

    /*
    响应信息
     */
    private String message;

    /*
    字典信息 检查点
     */
    private List<DataDictionaryVo> list;

    /*
    字典信息 错误文件
     */
    private List<DataDictionaryVo> listErrorFile;

    public List<DataDictionaryVo> getList() {
        return list;
    }

    public void setList(List<DataDictionaryVo> list) {
        this.list = list;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataDictionaryVo> getListErrorFile() {
        return listErrorFile;
    }

    public void setListErrorFile(List<DataDictionaryVo> listErrorFile) {
        this.listErrorFile = listErrorFile;
    }
}
