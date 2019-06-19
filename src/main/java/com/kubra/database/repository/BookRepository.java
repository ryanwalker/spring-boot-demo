package com.kubra.database.repository;

import com.kubra.database.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByYearPublishedBetween(Integer yearStart, Integer yearEnd);

  List<Book> findAllByAuthorContainingOrderByIsbn(String authorName);
}
