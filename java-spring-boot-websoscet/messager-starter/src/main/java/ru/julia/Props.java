package ru.julia;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "messager")
public record Props(String message) {}
