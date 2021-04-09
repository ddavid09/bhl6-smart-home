package pl.kurs.restapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.restapi.entities.PostEntity;
import pl.kurs.restapi.repository.PostRepository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class PostControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private PostRepository postRepository;
//
//
//    @Test
//    @Transactional
//    void shouldGetSinglePost() throws Exception {
//        // given
//        PostEntity newPostEntity = new PostEntity();
//        newPostEntity.setTitle("Test");
//        newPostEntity.setContent("Test content");
//        postRepository.save(newPostEntity);
//        // when
//        MvcResult mvcResult = mockMvc.perform(get("/posts/" + newPostEntity.getId()))
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andReturn();
//        // then
//        PostEntity postEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PostEntity.class);
//        assertThat(postEntity).isNotNull();
//        assertThat(postEntity.getId()).isEqualTo(newPostEntity.getId());
//        assertThat(postEntity.getTitle()).isEqualTo("Test");
//        assertThat(postEntity.getContent()).isEqualTo("Test content");
//
//    }
//
//
//    @Test
//    @Transactional
//    void shouldGetSinglePost2() throws Exception {
//        // given
//        // when
//        MvcResult mvcResult = mockMvc.perform(get("/posts/1"))
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andExpect(jsonPath("$.id", Matchers.is("1")))
//                .andExpect(jsonPath("$.title", Matchers.is("Test post 1")))
//                .andExpect(jsonPath("$.commentList", Matchers.hasSize(9)))
//                .andReturn();
//        // then
//    }
}