package com.myapp.todoactuator.config;

import com.myapp.todoactuator.interceptor.ToDoMetricInterceptor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
@EnableConfigurationProperties(ToDoProperties.class)
public class ToDoConfig {

    @Bean
    public MappedInterceptor metricInterceptor(MeterRegistry registry){
        return new MappedInterceptor(new String[]{"/**"},
                new ToDoMetricInterceptor(registry));
    }

}
