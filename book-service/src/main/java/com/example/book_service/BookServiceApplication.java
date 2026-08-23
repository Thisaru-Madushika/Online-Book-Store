package com.example.book_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@SpringBootApplication
public class BookServiceApplication {

    public static void main(String[] args) {
        System.out.println("🚀🚀🚀 FORCING MONGODB CONNECTION TO DOCKER 🚀🚀🚀");
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Bean
    public MongoClient mongoClient() {
        System.out.println("🔗🔗🔗 CONNECTING TO MONGODB CONTAINER 🔗🔗🔗");
        return MongoClients.create("mongodb://mongodb:27017/bookdb");
    }
}