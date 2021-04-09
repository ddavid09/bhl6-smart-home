package pl.kurs.restapi.services;

import pl.kurs.restapi.controllers.dto.request.PostRequest;
import pl.kurs.restapi.controllers.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    public List<PostResponse> getPosts(int pageNumber);
    public PostResponse getPost(String id);
    PostResponse addPost(PostRequest postRequest);
    PostResponse updatePost(PostRequest postRequest, String id);
    void delPost(String id);
    void clearPostsWithComments();
    PostResponse addComment(String content, String postId);
}
