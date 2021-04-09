package pl.kurs.restapi.controllers.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<CommentResponse> commentList;
}
