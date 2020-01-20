package com.lyang.dataconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FileName: ConsumerApplication
 * Author:   lujy7
 * Date:     2020/1/19 14:13
 * Description:
 */
@SpringBootApplication(scanBasePackages = {"com.lyang.dataconsumer"})
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
