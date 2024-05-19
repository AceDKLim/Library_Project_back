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
// import com.library.library.domain.User;
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

//     @BeforeEach
//     public void mockMvcSetup() {
//         this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//         reviewRepository.deleteAll();
//     }

//     @DisplayName("addReview : 리뷰 쓰기에 성공한다.")
//     @Test
//     public void addReview() throws Exception {
//         User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         final String url = "/api/reviews";
//         final String title = "title";
//         final String content = "content";
//         final String isbnNo = "isbnNo";
//         final String studentnumber = login_user.getStudentID();
//         final String score = "score";
//         final AddReviewRequest reviewRequest = new AddReviewRequest(login_user, title, content, isbnNo, score);

//         final String requestBody = objectMapper.writeValueAsString(reviewRequest);

//         ResultActions result = mockMvc
//                 .perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));
//         result.andExpect(status().isCreated());
//         List<Review> reviews = reviewRepository.findAll();

//         assertThat(reviews.size()).isEqualTo(1);
//         assertThat(reviews.get(0).getTitle()).isEqualTo(title);
//         assertThat(reviews.get(0).getContent()).isEqualTo(content);
//         assertThat(reviews.get(0).getIsbnNo()).isEqualTo(isbnNo);
//         assertThat(reviews.get(0).getStudentNumber()).isEqualTo(studentnumber);
//         assertThat(reviews.get(0).getScore()).isEqualTo(score);

//     }

//     @DisplayName("findAllReview: 리뷰 목록 조회에 성공한다.")
//     @Test
//     public void findAllReviews() throws Exception {
//         // given
//         final String url = "/api/reviews";
//         final String title = "title";
//         final String content = "content";
//         final String isbnNo = "isbnNo";
//         final String studentnumber = "studentnumber";
//         final String score = "score";
//         reviewRepository.save(Review.builder()
//                 .title(title).content(content).isbnNo(isbnNo).studentNumber(studentnumber).score(score).build());

//         // when
//         final ResultActions resultActions = mockMvc.perform(get(url)
//                 .accept(MediaType.APPLICATION_JSON));

//         // then
//         resultActions
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].content").value(content))
//                 .andExpect(jsonPath("$[0].title").value(title))
//                 .andExpect(jsonPath("$[0].isbnNo").value(isbnNo))
//                 .andExpect(jsonPath("$[0].studentnumber").value(studentnumber))
//                 .andExpect(jsonPath("$[0].score").value(score));
//     }

//     @DisplayName("findReview: 리뷰 글 조회에 성공한다.")
//     @Test
//     public void findReview() throws Exception {
//         // given
//         final String url = "/api/reviews/{id}";
//         final String title = "title";
//         final String content = "content";
//         final String isbnNo = "isbnNo";
//         final String studentnumber = "studentnumber";
//         final String score = "score";

//         Review savedReview = reviewRepository.save(Review.builder().title(title).content(content).isbnNo(isbnNo)
//                 .studentNumber(studentnumber).score(score).build());

//         // when
//         final ResultActions resultActions = mockMvc.perform(get(url, savedReview.getId()));

//         // then
//         resultActions
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.content").value(content))
//                 .andExpect(jsonPath("$.title").value(title))
//                 .andExpect(jsonPath("$.isbnNo").value(isbnNo))
//                 .andExpect(jsonPath("$.studentNumber").value(studentnumber))
//                 .andExpect(jsonPath("$.score").value(score));
//     }

//     @DisplayName("deleteReview: 리뷰 글 삭제에 성공한다.")
//     @Test
//     public void deleteReview() throws Exception {
//         // given
//         final String url = "/api/reviews/{id}";
//         final String title = "title";
//         final String content = "content";
//         final String isbnNo = "isbnNo";
//         final String studentnumber = "studentnumber";
//         final String score = "score";

//         Review savedReview = reviewRepository.save(Review.builder().title(title).content(content).isbnNo(isbnNo)
//                 .studentNumber(studentnumber).score(score).build());

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
//         final String isbnNo = "isbnNo";
//         final String studentnumber = "studentnumber";
//         final String score = "score";

//         Review savedReview = reviewRepository.save(Review.builder().title(title).content(content).isbnNo(isbnNo)
//                 .studentNumber(studentnumber).score(score).build());

//         final String newTitle = "new title";
//         final String newContent = "new content";
//         final String newIsbnNo = "isbnNo";
//         final String newStudentNumber = "studentnumber";
//         final String newScore = "score";

//         UpdateReviewRequest request = new UpdateReviewRequest(newTitle, newContent, newIsbnNo, newStudentNumber, newScore);
//         // when
//         ResultActions result = mockMvc.perform(put(url, savedReview.getId())
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .content(objectMapper.writeValueAsString(request)));
//         // then
//         result.andExpect(status().isOk());
//         Review review = reviewRepository.findById(savedReview.getId()).get();
//         assertThat(review.getTitle()).isEqualTo(newTitle);
//         assertThat(review.getContent()).isEqualTo(newContent);
//         assertThat(review.getIsbnNo()).isEqualTo(newIsbnNo);
//         assertThat(review.getStudentNumber()).isEqualTo(newStudentNumber);
//         assertThat(review.getScore()).isEqualTo(newScore);
//     }
// }
