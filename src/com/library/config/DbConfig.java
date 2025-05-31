package com.library.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    // 从配置文件加载数据库连接信息
    public static DbConfig loadFromProperties() {
        DbConfig dbConfig = new DbConfig();
        Properties properties = new Properties();

        try {
            // 使用绝对路径来加载配置文件
            String configFilePath = "E:/code/IntelliJ_IDEAProjects/LibraryNet/src/com/library/config/application.properties";  // 直接写绝对路径

            // 检查文件是否存在
            File configFile = new File(configFilePath);
            if (!configFile.exists()) {
                System.out.println("配置文件不存在: " + configFilePath);
                return null;
            }

            try (InputStream input = new FileInputStream(configFile)) {
                // 加载配置文件
                properties.load(input);

                dbConfig.setUrl(properties.getProperty("db.url"));
                dbConfig.setUsername(properties.getProperty("db.username"));
                dbConfig.setPassword(properties.getProperty("db.password"));
                dbConfig.setDriverClassName(properties.getProperty("db.driverClassName"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dbConfig;
    }

    // Getter and Setter methods
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}

