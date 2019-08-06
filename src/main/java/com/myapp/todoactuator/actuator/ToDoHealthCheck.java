package com.myapp.todoactuator.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ToDoHealthCheck implements HealthIndicator {

    @Value("${todo.path:/tmp}")
    private String path;

    @Override
    public Health health() {
        try{
            File file = new File(path);
            if(file.exists()){
                if (file.canWrite()){
                    return Health.up().build();
                } else {
                    return Health.down().build();
                }
            }else {
                return Health.outOfService().build();
            }
        }catch (Exception e){
            return Health.down(e).build();
        }
    }
}
