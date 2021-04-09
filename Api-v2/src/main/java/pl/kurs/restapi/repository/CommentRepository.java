package pl.kurs.restapi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kurs.restapi.entities.CommentEntity;
import pl.kurs.restapi.entities.PostEntity;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,String> {
    List<CommentEntity> findAllByPostEntityIn(List<PostEntity> postEntities);

//    @Query("select p from Post p where title = ?1")
//    List<Post> findAllByTitle(String title);
//    List<Post> findAllByTitle(String title);

//    @Query("Select p From Post p"+
//            " left join fetch p.commentList")
//    List<Post> findAllWithComments(Pageable page);

//    @Query("Select p From Post p")
//    List<Post> findAllWithComments(Pageable page);
}
