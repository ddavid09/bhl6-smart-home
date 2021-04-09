package pl.kurs.restapi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Size(min=5, message="Title must be at least 5 characters long")
    private String title;

    @NotNull
    @Size(min=5, message="Content must be at least 5 characters long")
    private String content;

    private LocalDateTime createdAt;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @BatchSize(size = 5)
    @JoinColumn(name = "post_id",insertable = false, updatable = false)
    private List<CommentEntity> commentEntityList;

    public PostEntity(@NotNull @Size(min = 5, message = "Title must be at least 5 characters long") String title, @NotNull @Size(min = 5, message = "Content must be at least 5 characters long") String content) {
        this.title = title;
        this.content = content;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

}
