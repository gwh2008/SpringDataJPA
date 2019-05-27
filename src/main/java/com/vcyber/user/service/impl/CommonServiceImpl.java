package com.vcyber.user.service.impl;

import com.vcyber.user.service.CommonService;
import org.springframework.stereotype.Component;
import javax.jws.WebService;

@WebService(serviceName = "CommonService", //与接口中指定的name一直
        targetNamespace = "http://service.user.vcyber.com/", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.vcyber.user.service.CommonService"//接口地址
)
@Component
public class CommonServiceImpl implements CommonService {
    @Override
    public String sayHello(String name) {
        return "世界Hello ,"+name;
    }
}
