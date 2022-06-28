package com.example.Repo;

import com.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Long> {
//    @Query("SELECT u FROM User u WHERE u.email =?1")
    User findByEmail(String email);

}
