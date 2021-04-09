package pl.kurs.restapi.services;

import org.springframework.stereotype.Service;

@Service
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HelloServiceImpl implements HelloService {

    public String helloWorld(){
        return "Hello World";
    }
}
