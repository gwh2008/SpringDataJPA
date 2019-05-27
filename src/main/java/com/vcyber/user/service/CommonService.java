package com.vcyber.user.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name="CommonService", //暴露服务名称
        targetNamespace = "http://service.user.vcyber.com/") //命名空间，一般是接口的倒叙
public interface CommonService {
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    public String sayHello(@WebParam(name = "userName") String name);

}