package com.eccom.cloud.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.Request;
import feign.Target;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class FeignConfig {

    @Bean
    public Request.Options connectTimeOut() {
        return new Request.Options(10000,10000);
    }


    @Bean
    public Feign.Builder feignHystrixBuilder() {
        return HystrixFeign.builder().setterFactory(new SetterFactory() {
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                return HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(UserService.class.getSimpleName()))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000) // 超时配置
                        );
            }
        });
    }
}
