package pl.kurs.restapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kurs.restapi.entities.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity,String> {
    //    @Query("select p from Post p where title = ?1")
    Page<PostEntity> findAll(Pageable pageable);

//    @Query("select p from Post p where title = ?1")
//    List<Post> findAllByTitle(String title);
//    List<Post> findAllByTitle(String title);

//    @Query("Select p From Post p"+
//            " left join fetch p.commentList")
//    List<Post> findAllWithComments(Pageable page);

//    @Query("Select p From Post p")
//    List<Post> findAllWithComments(Pageable page);
}
