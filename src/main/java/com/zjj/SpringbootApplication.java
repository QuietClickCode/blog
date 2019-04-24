package com.zjj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * @program: demo
 * @description: 启动类
 * @author: zjj
 * @create: 2019-04-23 22:05:58
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.zjj.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
