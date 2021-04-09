package pl.kurs.restapi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.kurs.restapi.security.ERole;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    public RoleEntity(ERole name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;


}
