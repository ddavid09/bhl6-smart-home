package pl.kurs.restapi.repository;


import org.springframework.data.repository.CrudRepository;
import pl.kurs.restapi.entities.RoleEntity;
import pl.kurs.restapi.security.ERole;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity,String> {
    Optional<RoleEntity> findByName(ERole name);
}
