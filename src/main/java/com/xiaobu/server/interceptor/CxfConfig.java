package com.xiaobu.server.interceptor;

import com.xiaobu.server.DemoService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/23 9:16
 * @description V1.0 CXF配置
 */
@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;

    @Autowired
    DemoService demoService;

    /**
     * @author xiaobu
     * @date 2018/11/23 14:38
     * @return javax.xml.ws.Endpoint
     * @descprition  EndpointImpl 引入的包为  org.apache.cxf.jaxws.EndpointImpl;
     * @version 1.0
     */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, demoService);
        endpoint.publish("/demoService");
        endpoint.getInInterceptors().add(new AuthInterceptor());
        return endpoint;
    }

}
