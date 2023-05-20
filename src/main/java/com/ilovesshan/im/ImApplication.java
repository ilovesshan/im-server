package com.ilovesshan.im;

import com.ilovesshan.im.core.netty.CoordinationNettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@ServletComponentScan
public class ImApplication implements CommandLineRunner {

    @Resource
    private CoordinationNettyServer nettyServer;

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);
    }

}
