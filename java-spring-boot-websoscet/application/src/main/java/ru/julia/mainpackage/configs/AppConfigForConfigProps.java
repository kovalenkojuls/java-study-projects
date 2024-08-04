package ru.julia.mainpackage.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record AppConfigForConfigProps(String paramName) {}
