// package com.library.library.controller;

// import java.util.List;

// import static org.assertj.core.api.Assertions.assertThat;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.library.library.domain.Book;
// import com.library.library.dto.AddBookRequest;
// import com.library.library.dto.UpdateBookRequest;
// import com.library.library.repository.BookRepository;

// @SpringBootTest
// @AutoConfigureMockMvc
// @WithMockUser(roles = ("ADMIN"))
// public class BookApiControllerTest {

//         @Autowired
//         protected MockMvc mockMvc;
//         @Autowired
//         protected ObjectMapper objectMapper;
//         @Autowired
//         protected WebApplicationContext context;
//         @Autowired
//         BookRepository bookRepository;

//         @BeforeEach
//         public void mockMvcSetup() {
//                 this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//                 bookRepository.deleteAll();
//         }

//         @DisplayName("addBook : 책 등록에 성공한다.")
//         @Test
//         public void testAddBook() throws Exception {
//                 final String url = "/api/books";
//                 final String isbnNo = "isbnNo";
//                 final String title = "title";
//                 final String author = "author";
//                 final String publish = "publish";
//                 final String pubyear = "pubyear";
//                 final String num = "num";
//                 final String location = "location";
//                 final String imageSrc = "imageSrc";
//                 final String detailSrc = "detailSrc";
//                 final String tags = "tags";
//                 final AddBookRequest bookRequest = new AddBookRequest(isbnNo, title, author, publish, pubyear, num,
//                                 location, imageSrc, detailSrc, tags);
//                 final String requestBody = objectMapper.writeValueAsString(bookRequest);

//                 ResultActions result = mockMvc
//                                 .perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));
//                 result.andExpect(status().isCreated());
//                 List<Book> books = bookRepository.findAll();

//                 assertThat(books.size()).isEqualTo(1);
//                 assertThat(books.get(0).getIsbnNo()).isEqualTo(isbnNo);
//                 assertThat(books.get(0).getTitle()).isEqualTo(title);
//                 assertThat(books.get(0).getAuthor()).isEqualTo(author);
//                 assertThat(books.get(0).getPublish()).isEqualTo(publish);
//                 assertThat(books.get(0).getPubyear()).isEqualTo(pubyear);
//                 assertThat(books.get(0).getNum()).isEqualTo(num);
//                 assertThat(books.get(0).getLocation()).isEqualTo(location);
//                 assertThat(books.get(0).getImageSrc()).isEqualTo(imageSrc);
//                 assertThat(books.get(0).getDetailSrc()).isEqualTo(detailSrc);
//                 assertThat(books.get(0).getTags()).isEqualTo(tags);
//         }

//         @DisplayName("findAllBooks: 책 목록 조회에 성공한다.")
//         @Test
//         public void testFindAllBooks() throws Exception {
//                 // given
//                 final String url = "/api/books";
//                 final String isbnNo = "isbnNo";
//                 final String title = "title";
//                 final String author = "author";
//                 final String publish = "publish";
//                 final String pubyear = "pubyear";
//                 final String num = "num";
//                 final String location = "location";
//                 final String imageSrc = "imageSrc";
//                 final String detailSrc = "detailSrc";
//                 final String tags = "tags";
//                 bookRepository.save(Book.builder().isbnNo(isbnNo).title(title).author(author).publish(publish)
//                                 .pubyear(pubyear)
//                                 .num(num).location(location).imageSrc(imageSrc).detailSrc(detailSrc).tags(tags)
//                                 .build());

//                 // when
//                 final ResultActions resultActions = mockMvc.perform(get(url)
//                                 .accept(MediaType.APPLICATION_JSON));

//                 // then
//                 resultActions
//                                 .andExpect(status().isOk())
//                                 .andExpect(jsonPath("$[0].isbnNo").value(isbnNo))
//                                 .andExpect(jsonPath("$[0].title").value(title))
//                                 .andExpect(jsonPath("$[0].author").value(author))
//                                 .andExpect(jsonPath("$[0].publish").value(publish))
//                                 .andExpect(jsonPath("$[0].pubyear").value(pubyear))
//                                 .andExpect(jsonPath("$[0].num").value(num))
//                                 .andExpect(jsonPath("$[0].location").value(location))
//                                 .andExpect(jsonPath("$[0].imageSrc").value(imageSrc))
//                                 .andExpect(jsonPath("$[0].detailSrc").value(detailSrc))
//                                 .andExpect(jsonPath("$[0].tags").value(tags));
//         }

//         @DisplayName("findBook: 책 조회에 성공한다.")
//         @Test
//         public void testFindBook() throws Exception {
//                 // given
//                 final String url = "/api/books/{isbnNo}";
//                 final String isbnNo = "isbnNo";
//                 final String title = "title";
//                 final String author = "author";
//                 final String publish = "publish";
//                 final String pubyear = "pubyear";
//                 final String num = "num";
//                 final String location = "location";
//                 final String imageSrc = "imageSrc";
//                 final String detailSrc = "detailSrc";
//                 final String tags = "tags";

//                 Book savedBook = bookRepository
//                                 .save(Book.builder().isbnNo(isbnNo).title(title).author(author).publish(publish)
//                                                 .pubyear(pubyear)
//                                                 .num(num).location(location).imageSrc(imageSrc).detailSrc(detailSrc)
//                                                 .tags(tags).build());

//                 // when
//                 final ResultActions resultActions = mockMvc.perform(get(url, savedBook.getIsbnNo()));

//                 // then
//                 resultActions
//                                 .andExpect(status().isOk())
//                                 .andExpect(jsonPath("$.isbnNo").value(isbnNo))
//                                 .andExpect(jsonPath("$.title").value(title))
//                                 .andExpect(jsonPath("$.author").value(author))
//                                 .andExpect(jsonPath("$.publish").value(publish))
//                                 .andExpect(jsonPath("$.pubyear").value(pubyear))
//                                 .andExpect(jsonPath("$.num").value(num))
//                                 .andExpect(jsonPath("$.location").value(location))
//                                 .andExpect(jsonPath("$.imageSrc").value(imageSrc))
//                                 .andExpect(jsonPath("$.detailSrc").value(detailSrc))
//                                 .andExpect(jsonPath("$.tags").value(tags));
//         }

//         @DisplayName("deleteBook: 책 삭제에 성공한다.")
//         @Test
//         public void testDeleteReview() throws Exception {
//                 // given
//                 final String url = "/api/books/{isbnNo}";
//                 final String isbnNo = "isbnNo";
//                 final String title = "title";
//                 final String author = "author";
//                 final String publish = "publish";
//                 final String pubyear = "pubyear";
//                 final String num = "num";
//                 final String location = "location";
//                 final String imageSrc = "imageSrc";
//                 final String detailSrc = "detailSrc";
//                 final String tags = "tags";

//                 Book savedBook = bookRepository
//                                 .save(Book.builder().isbnNo(isbnNo).title(title).author(author).publish(publish)
//                                                 .pubyear(pubyear)
//                                                 .num(num).location(location).imageSrc(imageSrc).detailSrc(detailSrc)
//                                                 .tags(tags).build());

//                 // when
//                 mockMvc.perform(delete(url, savedBook.getIsbnNo()))
//                                 .andExpect(status().isOk());

//                 // then
//                 List<Book> books = bookRepository.findAll();

//                 assertThat(books).isEmpty();
//         }

//         @DisplayName("updateBook: 책 수정에 성공한다.")
//         @Test
//         public void testUpdateReview() throws Exception {
//                 // given
//                 final String url = "/api/books/{isbnNo}";
//                 final String isbnNo = "isbnNo";
//                 final String title = "title";
//                 final String author = "author";
//                 final String publish = "publish";
//                 final String pubyear = "pubyear";
//                 final String num = "num";
//                 final String location = "location";
//                 final String imageSrc = "imageSrc";
//                 final String detailSrc = "detailSrc";
//                 final String tags = "tags";

//                 Book savedBook = bookRepository
//                                 .save(Book.builder().isbnNo(isbnNo).title(title).author(author).publish(publish)
//                                                 .pubyear(pubyear)
//                                                 .num(num).location(location).imageSrc(imageSrc).detailSrc(detailSrc)
//                                                 .tags(tags).build());

//                 final String newTitle = "newTitle";
//                 final String newAuthor = "newAuthor";
//                 final String newPublish = "newPublish";
//                 final String newpubyear = "newpubyear";
//                 final String newNum = "newNum";
//                 final String newLocation = "newLocation";
//                 final String newImageSrc = "newImageSrc";
//                 final String newDetailSrc = "newDetailSrc";
//                 final String newTags = "newTags";
//                 UpdateBookRequest request = new UpdateBookRequest(newTitle, newAuthor, newPublish, newpubyear, newNum,
//                                 newLocation, newImageSrc, newDetailSrc, newTags);
//                 // when
//                 ResultActions result = mockMvc.perform(put(url, savedBook.getIsbnNo())
//                                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                 .content(objectMapper.writeValueAsString(request)));
//                 // then
//                 result.andExpect(status().isOk());
//                 Book book = bookRepository.findById(savedBook.getIsbnNo()).get();
//                 assertThat(book.getTitle()).isEqualTo(newTitle);
//                 assertThat(book.getAuthor()).isEqualTo(newAuthor);
//                 assertThat(book.getPublish()).isEqualTo(newPublish);
//                 assertThat(book.getPubyear()).isEqualTo(newpubyear);
//                 assertThat(book.getNum()).isEqualTo(newNum);
//                 assertThat(book.getLocation()).isEqualTo(newLocation);
//                 assertThat(book.getImageSrc()).isEqualTo(newImageSrc);
//                 assertThat(book.getDetailSrc()).isEqualTo(newDetailSrc);
//                 assertThat(book.getTags()).isEqualTo(newTags);
//         }

// }
