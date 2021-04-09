package pl.kurs.restapi.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

public interface HelloService {

    public String helloWorld();
}
