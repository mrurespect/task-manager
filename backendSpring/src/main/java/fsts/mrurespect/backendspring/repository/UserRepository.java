package fsts.mrurespect.backendspring.repository;

import fsts.mrurespect.backendspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    Boolean existsUserByEmail(String email);
    User getUserByEmailAndPassword(String email,String password);
    User getUserByEmail(String email);

}
