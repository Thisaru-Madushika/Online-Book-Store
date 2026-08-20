package com.example.book_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
public class Book {
    
    @Id
    private String id;
    
    private String title;
    private String author;
    private double price;
    private int stock;
}