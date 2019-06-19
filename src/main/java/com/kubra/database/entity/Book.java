package com.kubra.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  Long id;

  String name;

  String author;

  Integer yearPublished;

  String isbn;

  public Book() {
  }

  public Book(String name, String author, Integer yearPublished, String isbn) {
    this.name = name;
    this.author = author;
    this.yearPublished = yearPublished;
    this.isbn = isbn;
  }
}
