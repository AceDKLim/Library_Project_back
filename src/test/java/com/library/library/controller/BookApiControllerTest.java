package com.library.library.controller;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library.domain.Book;
import com.library.library.dto.AddBookRequest;
import com.library.library.dto.UpdateBookRequest;
import com.library.library.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WebApplicationContext context;
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        bookRepository.deleteAll();
    }

    @DisplayName("addBook : 책 등록에 성공한다.")
    @Test
    public void testAddBook() throws Exception {
        final String url = "/api/books";
        final String isbn_no = "isbn_no";
        final String title = "title";
        final String author = "author";
        final String publish = "publish";
        final String p_year = "p_year";
        final String num = "num";
        final String location = "location";
        final String image_src = "image_src";
        final String detail_src = "detail_src";
        final String tags = "tags";
        final AddBookRequest bookRequest = new AddBookRequest(isbn_no, title, author, publish, p_year, num, location,
                image_src, detail_src, tags);

        final String requestBody = objectMapper.writeValueAsString(bookRequest);

        ResultActions result = mockMvc
                .perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));
        result.andExpect(status().isCreated());
        List<Book> books = bookRepository.findAll();

        assertThat(books.size()).isEqualTo(1);
        assertThat(books.get(0).getIsbn_no()).isEqualTo(isbn_no);
        assertThat(books.get(0).getTitle()).isEqualTo(title);
        assertThat(books.get(0).getAuthor()).isEqualTo(author);
        assertThat(books.get(0).getPublish()).isEqualTo(publish);
        assertThat(books.get(0).getP_year()).isEqualTo(p_year);
        assertThat(books.get(0).getNum()).isEqualTo(num);
        assertThat(books.get(0).getLocation()).isEqualTo(location);
        assertThat(books.get(0).getImage_src()).isEqualTo(image_src);
        assertThat(books.get(0).getDetail_src()).isEqualTo(detail_src);
        assertThat(books.get(0).getTags()).isEqualTo(tags);
    }

    @DisplayName("findAllBooks: 책 목록 조회에 성공한다.")
    @Test
    public void testFindAllBooks() throws Exception {
        // given
        final String url = "/api/books";
        final String isbn_no = "isbn_no";
        final String title = "title";
        final String author = "author";
        final String publish = "publish";
        final String p_year = "p_year";
        final String num = "num";
        final String location = "location";
        final String image_src = "image_src";
        final String detail_src = "detail_src";
        final String tags = "tags";
        bookRepository.save(Book.builder().isbn_no(isbn_no).title(title).author(author).publish(publish).p_year(p_year)
                .num(num).location(location).image_src(image_src).detail_src(detail_src).tags(tags).build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn_no").value(isbn_no))
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].author").value(author))
                .andExpect(jsonPath("$[0].publish").value(publish))
                .andExpect(jsonPath("$[0].p_year").value(p_year))
                .andExpect(jsonPath("$[0].num").value(num))
                .andExpect(jsonPath("$[0].location").value(location))
                .andExpect(jsonPath("$[0].image_src").value(image_src))
                .andExpect(jsonPath("$[0].detail_src").value(detail_src))
                .andExpect(jsonPath("$[0].tags").value(tags));
    }

    @DisplayName("findBook: 책 조회에 성공한다.")
    @Test
    public void testFindBook() throws Exception {
        // given
        final String url = "/api/books/{isbn_no}";
        final String isbn_no = "isbn_no";
        final String title = "title";
        final String author = "author";
        final String publish = "publish";
        final String p_year = "p_year";
        final String num = "num";
        final String location = "location";
        final String image_src = "image_src";
        final String detail_src = "detail_src";
        final String tags = "tags";

        Book savedBook = bookRepository
                .save(Book.builder().isbn_no(isbn_no).title(title).author(author).publish(publish).p_year(p_year)
                        .num(num).location(location).image_src(image_src).detail_src(detail_src).tags(tags).build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedBook.getIsbn_no()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn_no").value(isbn_no))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.author").value(author))
                .andExpect(jsonPath("$.publish").value(publish))
                .andExpect(jsonPath("$.p_year").value(p_year))
                .andExpect(jsonPath("$.num").value(num))
                .andExpect(jsonPath("$.location").value(location))
                .andExpect(jsonPath("$.image_src").value(image_src))
                .andExpect(jsonPath("$.detail_src").value(detail_src))
                .andExpect(jsonPath("$.tags").value(tags));
    }

    @DisplayName("deleteBook: 책 삭제에 성공한다.")
    @Test
    public void testDeleteReview() throws Exception {
        // given
        final String url = "/api/books/{isbn_no}";
        final String isbn_no = "isbn_no";
        final String title = "title";
        final String author = "author";
        final String publish = "publish";
        final String p_year = "p_year";
        final String num = "num";
        final String location = "location";
        final String image_src = "image_src";
        final String detail_src = "detail_src";
        final String tags = "tags";

        Book savedBook = bookRepository
                .save(Book.builder().isbn_no(isbn_no).title(title).author(author).publish(publish).p_year(p_year)
                        .num(num).location(location).image_src(image_src).detail_src(detail_src).tags(tags).build());

        // when
        mockMvc.perform(delete(url, savedBook.getIsbn_no()))
                .andExpect(status().isOk());

        // then
        List<Book> books = bookRepository.findAll();

        assertThat(books).isEmpty();
    }

    @DisplayName("updateBook: 책 수정에 성공한다.")
    @Test
    public void testUpdateReview() throws Exception {
        // given
        final String url = "/api/books/{isbn_no}";
        final String isbn_no = "isbn_no";
        final String title = "title";
        final String author = "author";
        final String publish = "publish";
        final String p_year = "p_year";
        final String num = "num";
        final String location = "location";
        final String image_src = "image_src";
        final String detail_src = "detail_src";
        final String tags = "tags";

        Book savedBook = bookRepository
                .save(Book.builder().isbn_no(isbn_no).title(title).author(author).publish(publish).p_year(p_year)
                        .num(num).location(location).image_src(image_src).detail_src(detail_src).tags(tags).build());

        final String newTitle = "newTitle";
        final String newAuthor = "newAuthor";
        final String newPublish = "newPublish";
        final String newp_year = "newp_year";
        final String newNum = "newNum";
        final String newLocation = "newLocation";
        final String newImage_src = "newImage_src";
        final String newDetail_src = "newDetail_src";
        final String newTags = "newTags";
        UpdateBookRequest request = new UpdateBookRequest(newTitle, newAuthor, newPublish, newp_year, newNum, newLocation, newImage_src, newDetail_src, newTags);
        // when
        ResultActions result = mockMvc.perform(put(url, savedBook.getIsbn_no())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));
        // then
        result.andExpect(status().isOk());
        Book book = bookRepository.findById(savedBook.getIsbn_no()).get();
        assertThat(book.getTitle()).isEqualTo(newTitle);
        assertThat(book.getAuthor()).isEqualTo(newAuthor);
        assertThat(book.getPublish()).isEqualTo(newPublish);
        assertThat(book.getP_year()).isEqualTo(newp_year);
        assertThat(book.getNum()).isEqualTo(newNum);
        assertThat(book.getLocation()).isEqualTo(newLocation);
        assertThat(book.getImage_src()).isEqualTo(newImage_src);
        assertThat(book.getDetail_src()).isEqualTo(newDetail_src);
        assertThat(book.getTags()).isEqualTo(newTags);
    }

}
