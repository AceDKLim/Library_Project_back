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
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
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
// import com.library.library.domain.Review;
// import com.library.library.dto.AddReviewRequest;
// import com.library.library.dto.UpdateReviewRequest;
// import com.library.library.repository.ReviewRepository;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class ReviewApiControllerTest {

//     @Autowired
//     protected MockMvc mockMvc;
//     @Autowired
//     protected ObjectMapper objectMapper;
//     @Autowired
//     protected WebApplicationContext context;
//     @Autowired
//     ReviewRepository reviewRepository;

//     @Autowired
//     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//     UserDetails userDetails = (UserDetails) principal;

//     @BeforeEach
//     public void mockMvcSetup() {
//         this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//         reviewRepository.deleteAll();
//     }

//     @DisplayName("addReview : 리뷰 쓰기에 성공한다.")
//     @Test
//     public void addReview() throws Exception {
//         final String url = "/api/reviews";
//         final String title = "title";
//         final String content = "content";
//         final String isbn_no = "isbn_no";
//         final String student_number = userDetails.getUsername();
//         final String score = "score";
//         final AddReviewRequest reviewRequest = new AddReviewRequest(principal, userDetails, title, content, isbn_no, score);

//         final String requestBody = objectMapper.writeValueAsString(reviewRequest);

//         ResultActions result = mockMvc
//                 .perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));
//         result.andExpect(status().isCreated());
//         List<Review> reviews = reviewRepository.findAll();

//         assertThat(reviews.size()).isEqualTo(1);
//         assertThat(reviews.get(0).getTitle()).isEqualTo(title);
//         assertThat(reviews.get(0).getContent()).isEqualTo(content);
//         assertThat(reviews.get(0).getIsbn_no()).isEqualTo(isbn_no);
//         assertThat(reviews.get(0).getStudent_number()).isEqualTo(student_number);
//         assertThat(reviews.get(0).getScore()).isEqualTo(score);

//     }

//     @DisplayName("findAllReview: 리뷰 목록 조회에 성공한다.")
//     @Test
//     public void findAllReviews() throws Exception {
//         // given
//         final String url = "/api/reviews";
//         final String title = "title";
//         final String content = "content";
//         final String isbn_no = "isbn_no";
//         final String student_number = "student_number";
//         final String score = "score";
//         reviewRepository.save(Review.builder()
//                 .title(title).content(content).isbn_no(isbn_no).student_number(student_number).score(score).build());

//         // when
//         final ResultActions resultActions = mockMvc.perform(get(url)
//                 .accept(MediaType.APPLICATION_JSON));

//         // then
//         resultActions
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].content").value(content))
//                 .andExpect(jsonPath("$[0].title").value(title))
//                 .andExpect(jsonPath("$[0].isbn_no").value(isbn_no))
//                 .andExpect(jsonPath("$[0].student_number").value(student_number))
//                 .andExpect(jsonPath("$[0].score").value(score));
//     }

//     @DisplayName("findReview: 리뷰 글 조회에 성공한다.")
//     @Test
//     public void findReview() throws Exception {
//         // given
//         final String url = "/api/reviews/{id}";
//         final String title = "title";
//         final String content = "content";
//         final String isbn_no = "isbn_no";
//         final String student_number = "student_number";
//         final String score = "score";

//         Review savedReview = reviewRepository.save(Review.builder().title(title).content(content).isbn_no(isbn_no)
//                 .student_number(student_number).score(score).build());

//         // when
//         final ResultActions resultActions = mockMvc.perform(get(url, savedReview.getId()));

//         // then
//         resultActions
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.content").value(content))
//                 .andExpect(jsonPath("$.title").value(title))
//                 .andExpect(jsonPath("$.isbn_no").value(isbn_no))
//                 .andExpect(jsonPath("$.student_number").value(student_number))
//                 .andExpect(jsonPath("$.score").value(score));
//     }

//     @DisplayName("deleteReview: 리뷰 글 삭제에 성공한다.")
//     @Test
//     public void deleteReview() throws Exception {
//         // given
//         final String url = "/api/reviews/{id}";
//         final String title = "title";
//         final String content = "content";
//         final String isbn_no = "isbn_no";
//         final String student_number = "student_number";
//         final String score = "score";

//         Review savedReview = reviewRepository.save(Review.builder().title(title).content(content).isbn_no(isbn_no)
//                 .student_number(student_number).score(score).build());

//         // when
//         mockMvc.perform(delete(url, savedReview.getId()))
//                 .andExpect(status().isOk());

//         // then
//         List<Review> reviews = reviewRepository.findAll();

//         assertThat(reviews).isEmpty();
//     }

//     @DisplayName("updateReview: 블로그 글 수정에 성공한다.")
//     @Test
//     public void updateReview() throws Exception {
//         // given
//         final String url = "/api/reviews/{id}";
//         final String title = "title";
//         final String content = "content";
//         final String isbn_no = "isbn_no";
//         final String student_number = "student_number";
//         final String score = "score";

//         Review savedReview = reviewRepository.save(Review.builder().title(title).content(content).isbn_no(isbn_no)
//                 .student_number(student_number).score(score).build());

//         final String newTitle = "new title";
//         final String newContent = "new content";
//         final String newIsbn_no = "isbn_no";
//         final String newStudent_number = "student_number";
//         final String newScore = "score";

//         UpdateReviewRequest request = new UpdateReviewRequest(newTitle, newContent, newIsbn_no, newStudent_number, newScore);
//         // when
//         ResultActions result = mockMvc.perform(put(url, savedReview.getId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .content(objectMapper.writeValueAsString(request)));
//         // then
//         result.andExpect(status().isOk());
//         Review review = reviewRepository.findById(savedReview.getId()).get();
//         assertThat(review.getTitle()).isEqualTo(newTitle);
//         assertThat(review.getContent()).isEqualTo(newContent);
//         assertThat(review.getIsbn_no()).isEqualTo(newIsbn_no);
//         assertThat(review.getStudent_number()).isEqualTo(newStudent_number);
//         assertThat(review.getScore()).isEqualTo(newScore);
//     }
// }
