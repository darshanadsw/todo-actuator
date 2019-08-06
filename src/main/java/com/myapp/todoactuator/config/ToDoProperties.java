package com.myapp.todoactuator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "todo")
public class ToDoProperties {
    String path;
}
