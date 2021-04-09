package pl.kurs.restapi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="id")
    private String id;
    private String username;
    private String email;
    private String password;
    private Boolean enabled;

    public UserEntity(String username, String email, String password, Boolean enabled, Set<RoleEntity> roleEntities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roleEntities = roleEntities;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(	name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleEntities = new HashSet<>();
}
