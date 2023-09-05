package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    User findByEmail(String email);

    boolean existsByEmail(String email);
    User findUserById (String Id);
}
