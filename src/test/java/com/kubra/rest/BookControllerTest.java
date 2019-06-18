package com.kubra.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubra.database.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  public void bookCreateAndReadTest() throws Exception {
    Book warAndPeace = new Book("War and Peace", "Leo Tolstoy", 1869, "978-1400079988");

    this.mvc.perform(get("/book").with(user("admin").password("pass")))
        .andExpect(status().isOk())
        .andExpect(content().string("[]"));

    // add a book to the collection
    this.mvc.perform(post("/book").with(user("admin").password("pass"))
        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(warAndPeace)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is(warAndPeace.getName())))
        .andExpect(jsonPath("$.author", is(warAndPeace.getAuthor())))
        .andExpect(jsonPath("$.yearPublished", is(warAndPeace.getYearPublished())))
        .andExpect(jsonPath("$.isbn", is(warAndPeace.getIsbn())));

    // verify a search outside the publication year bounds returns no results
    this.mvc.perform(get("/book").with(user("admin").password("pass"))
        .param("end", String.valueOf(1800)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));

    // verify a search of the proper year range returns the book
    this.mvc.perform(get("/book").with(user("admin").password("pass"))
        .param("end", String.valueOf(1900)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].name", is(warAndPeace.getName())))
        .andExpect(jsonPath("$[0].author", is(warAndPeace.getAuthor())))
        .andExpect(jsonPath("$[0].yearPublished", is(warAndPeace.getYearPublished())))
        .andExpect(jsonPath("$[0].isbn", is(warAndPeace.getIsbn())));
  }
}
