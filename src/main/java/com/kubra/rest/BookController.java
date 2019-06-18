package com.kubra.rest;

import com.kubra.database.entity.Book;
import com.kubra.database.repository.BookRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "/book",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class BookController {

  private BookRepository bookRepository;

  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping(path = "/all")
  public List<Book> getBooks() {
    return bookRepository.findAll();
  }

  @GetMapping
  public List<Book> getBooksPublishedBetween(
      @RequestParam(required = false) Integer start,
      @RequestParam(required = false) Integer end) {
    return bookRepository.findAllByYearPublishedBetween(
        Optional.ofNullable(start).orElse(0),
        Optional.ofNullable(end).orElse(LocalDateTime.now().getYear()));
  }

  @PostMapping
  public Book addBook(@RequestBody Book book) {
    return bookRepository.saveAndFlush(book);
  }
}
