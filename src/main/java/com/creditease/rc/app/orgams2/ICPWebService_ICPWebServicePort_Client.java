
package com.creditease.rc.app.orgams2;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.9
 * Wed Sep 03 10:56:53 CST 2014
 * Generated source version: 2.2.9
 * 
 */

public final class ICPWebService_ICPWebServicePort_Client {

    private static final QName SERVICE_NAME = new QName("http://service.icp.ws.component.creditease.com/", "ICPWebServiceService");

    private ICPWebService_ICPWebServicePort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = ICPWebServiceService.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ICPWebServiceService ss = new ICPWebServiceService(wsdlURL, SERVICE_NAME);
        ICPWebService port = ss.getICPWebServicePort();  
        
        {
        System.out.println("Invoking synBatchBorrowContract...");
        java.util.List<com.creditease.rc.app.orgams2.BorrowContractReq> _synBatchBorrowContract_requestList = null;
        java.util.List<com.creditease.rc.app.orgams2.BorrowContractRes> _synBatchBorrowContract__return = port.synBatchBorrowContract(_synBatchBorrowContract_requestList);
        System.out.println("synBatchBorrowContract.result=" + _synBatchBorrowContract__return);


        }
        {
        System.out.println("Invoking synBorrowContract...");
        com.creditease.rc.app.orgams2.BorrowContractReq _synBorrowContract_request = null;
        com.creditease.rc.app.orgams2.BorrowContractRes _synBorrowContract__return = port.synBorrowContract(_synBorrowContract_request);
        System.out.println("synBorrowContract.result=" + _synBorrowContract__return);


        }
        {
        System.out.println("Invoking synBorrowContractState...");
        java.util.List<com.creditease.rc.app.orgams2.ContractStateReq> _synBorrowContractState_request = null;
        com.creditease.rc.app.orgams2.ContractStateRes _synBorrowContractState__return = port.synBorrowContractState(_synBorrowContractState_request);
        System.out.println("synBorrowContractState.result=" + _synBorrowContractState__return);


        }

        System.exit(0);
    }

}