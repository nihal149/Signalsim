package com.example.Signalslim.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/signalsim_db?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root"); // Remplacez par votre utilisateur DB
        dataSource.setPassword(""); // Remplacez par votre mot de passe DB
        return dataSource;
    }
}
