package com.len;

import com.len.core.SocketThread.EchoServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author zhuxiaomeng
 * @date 2018/1/1.
 * @email 154040976@qq.com
 */

@EnableWebMvc
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan({"com.len","org.activiti"})
@MapperScan(basePackages = {"com.len.mapper"})
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@Import({Socket.class})
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    //  String[] names = applicationContext.getBeanDefinitionNames();
        //1.8 forEach循环
     //  Arrays.asList(names).forEach(System.out::println)
    try {
        new EchoServer().service();
    }catch (IOException e){
        e.printStackTrace();
    }
    }





}
