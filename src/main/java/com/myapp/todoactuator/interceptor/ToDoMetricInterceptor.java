package com.myapp.todoactuator.interceptor;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ToDoMetricInterceptor implements HandlerInterceptor {

    private MeterRegistry registry;
    private String URI;
    private String METHOD;
    private String pathKey;

    public ToDoMetricInterceptor(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        URI = request.getRequestURI();
        METHOD = request.getMethod();

        if(!URI.contains("prometheus")){
            log.info(" >> PATH: {}",URI);
            log.info(" >> METHOD {}",METHOD);
            pathKey = "api_".concat(METHOD.toLowerCase())
                    .concat(URI.replaceAll("/","_").toLowerCase());
            this.registry.counter(pathKey).increment();

        }

    }
}
