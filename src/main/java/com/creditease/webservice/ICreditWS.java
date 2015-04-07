package com.creditease.webservice;

import java.text.ParseException;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.ReceiveCreditMsg;

@WebService
public interface ICreditWS {

	public Message updateStatusByReceiveCreditMsg(@WebParam(name = "receiveCreditMsg") ReceiveCreditMsg receiveCreditMsg)  throws ParseException;
}
