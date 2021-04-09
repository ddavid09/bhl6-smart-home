package pl.kurs.restapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.kurs.restapi.controllers.dto.request.PostRequest;
import pl.kurs.restapi.controllers.dto.response.CommentResponse;
import pl.kurs.restapi.controllers.dto.response.PostResponse;
import pl.kurs.restapi.entities.CommentEntity;
import pl.kurs.restapi.entities.PostEntity;
import pl.kurs.restapi.repository.CommentRepository;
import pl.kurs.restapi.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@ConfigurationProperties(prefix = "app.posts")
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    final PostRepository postRepository;
    final CommentRepository commentRepository;

    private int pageSize = 5;
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    @Override
//    @Cacheable(cacheNames = "PostsWithComments")
    public List<PostResponse> getPosts(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by("createdAt").descending());
        Page<PostEntity> postEntityPage =  postRepository.findAll(pageable);
        return postEntityPage.map(postEntity -> new PostResponse(
                                postEntity.getId(),
                                postEntity.getTitle(),
                                postEntity.getContent(),
                                postEntity.getCreatedAt(),
                                postEntity.getCommentEntityList()==null?null:
                                        postEntity.getCommentEntityList().stream().map(
                                            commentEntity ->
                                                    new CommentResponse(
                                                            commentEntity.getId(),
                                                            commentEntity.getContent(),
                                                            commentEntity.getCreatedAt()
                                                    )
                                        ).collect(Collectors.toList())
                        )
                ).stream().collect(Collectors.toList());
    }


    @Override
//    @Cacheable(cacheNames = "PostsWithComments", key = "#id")
    public PostResponse getPost(String id) throws NoSuchElementException {
        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getCreatedAt(),
                postEntity.getCommentEntityList()==null?null:
                    postEntity.getCommentEntityList().stream().map(
                        commentEntity ->
                                new CommentResponse(
                                        commentEntity.getId(),
                                        commentEntity.getContent(),
                                        commentEntity.getCreatedAt()
                                )
                    ).collect(Collectors.toList())
        );

    }

//    @CachePut(cacheNames = "PostsWithComments", key = "#result.id")
    @Override
    public PostResponse addPost(PostRequest postRequest) {
        PostEntity postEntity = postRepository.save(
                new PostEntity(
                        postRequest.getTitle(),
                        postRequest.getContent()
                )
        );
        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getCreatedAt(),
                postEntity.getCommentEntityList()==null?null:
                    postEntity.getCommentEntityList().stream().map(
                        commentEntity ->
                                new CommentResponse(
                                        commentEntity.getId(),
                                        commentEntity.getContent(),
                                        commentEntity.getCreatedAt()
                                )
                    ).collect(Collectors.toList())
        );
    }

    @Transactional
//    @CachePut(cacheNames =  "PostsWithComments", key = "#result.id")
    @Override
    public PostResponse updatePost(PostRequest postRequest, String id) throws NoSuchElementException {
        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        postEntity.setContent(postRequest.getContent());
        postEntity.setTitle(postRequest.getTitle());
        postRepository.save(postEntity);
        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getCreatedAt(),
                postEntity.getCommentEntityList()==null?null:
                    postEntity.getCommentEntityList().stream().map(
                        commentEntity ->
                                new CommentResponse(
                                        commentEntity.getId(),
                                        commentEntity.getContent(),
                                        commentEntity.getCreatedAt()
                                )
                    ).collect(Collectors.toList())
        );
    }

//    @CacheEvict(cacheNames =  "PostsWithComments")
    @Override
    public void delPost(String id) throws NoSuchElementException{
        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        postRepository.deleteById(id);
    }

    @Override
//    @Cacheable(cacheNames = "PostsWithComments")
    public void clearPostsWithComments(){

    }

    @Override
//    @Transactional
//    @CachePut(cacheNames = "PostsWithComments", key = "#result.id")
    public PostResponse addComment(String content, String postId) throws NoSuchElementException  {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow();
        CommentEntity commentEntity = new CommentEntity(
                postEntity,
                content
        );
        List<CommentEntity> commentEntityList = postEntity.getCommentEntityList();
        commentRepository.save(commentEntity);
        commentEntityList.add(commentEntity);
        postEntity.setCommentEntityList(commentEntityList);
        postRepository.save(postEntity);

        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getCreatedAt(),
                commentEntityList.stream().map(
                    commentEntity2 ->
                            new CommentResponse(
                                    commentEntity2.getId(),
                                    commentEntity2.getContent(),
                                    commentEntity2.getCreatedAt()
                            )
                ).collect(Collectors.toList())
        );
    }

}
