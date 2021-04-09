package pl.kurs.restapi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kurs.restapi.controllers.dto.response.PostResponse;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void shouldGetSinglePost() {
        // given
        // when
        System.out.println("Sdasdds1");
        PostResponse singlePost = postService.getPost("1");
        System.out.println("Sdasdds2");
        // then
        assertThat(singlePost.getId()).isEqualTo("1");
        System.out.println("Sdasdds3");
        assertThat(singlePost.getContent()).isEqualTo("Content 1");
        System.out.println("Sdasdds4");
    }
}