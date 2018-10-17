package io.nambm.sachviet;

import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class SachvietApplication {

    public static void main(String[] args) {
        SpringApplication.run(SachvietApplication.class, args);
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionFactory getSessionFactory() {
        return GenericRepositoryImpl.getFactory();
    }
}
