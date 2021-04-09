package pl.kurs.restapi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne()
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @NotNull
    @Size(min=5, message="Content must be at least 5 characters long")
    private String content;


    private LocalDateTime createdAt;

    public CommentEntity(PostEntity postEntity, @NotNull @Size(min = 5, message = "Content must be at least 5 characters long") String content) {
        this.postEntity = postEntity;
        this.content = content;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

}
