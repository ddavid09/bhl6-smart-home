package pl.kurs.restapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.kurs.restapi.controllers.dto.request.PostRequest;
import pl.kurs.restapi.controllers.dto.response.PostResponse;
import pl.kurs.restapi.services.PostService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public ResponseEntity<List<PostResponse>> getPosts(@RequestParam(required = false) Optional<Integer> page){
        int pageNumber = 0;
        if(page.isPresent()){
            pageNumber = page.get()>=0 ? page.get():0 ;
        }

        return new ResponseEntity<>(postService.getPosts(pageNumber), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable String id){
        try{
            PostResponse postResponse = postService.getPost(id);
            return new ResponseEntity<>(postResponse, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PostMapping
    public ResponseEntity<PostResponse> addPost(@RequestBody PostRequest postRequest){
        return new ResponseEntity<>(postService.addPost(postRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("{id}")
    public ResponseEntity<PostResponse> putPost(@RequestBody PostRequest postRequest, @PathVariable String id){
        try{
            PostResponse postResponse = postService.updatePost(postRequest,id);
            return new ResponseEntity<>(postResponse, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{id}")
    public ResponseEntity delPost(@PathVariable String id){
        try{
            postService.delPost(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("{postId}/add_comment")
    public ResponseEntity<PostResponse> addComment(@RequestParam String content, @PathVariable String postId){
        try{
            PostResponse postResponse = postService.addComment(content,postId);
            return new ResponseEntity<>(postResponse, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
