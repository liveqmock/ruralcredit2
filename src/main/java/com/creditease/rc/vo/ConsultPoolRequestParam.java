package com.creditease.rc.vo;

import com.creditease.rc.domain.CustomerConsultPool;

import java.util.List;

public class ConsultPoolRequestParam extends WebServiceMessage{

    private CustomerConsultPool pool;

    public CustomerConsultPool getPool() {
        return pool;
    }

    public void setPool(CustomerConsultPool pool) {
        this.pool = pool;
    }
}
