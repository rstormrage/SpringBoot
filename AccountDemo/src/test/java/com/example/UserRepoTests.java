package com.example;

import com.example.Entity.User;
import com.example.Repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UserRepoTests {
    @Autowired
    private UserRepo repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user1 = new User();
        user1.setEmail("zzzlll1995@gmail.com");
        user1.setPassword("zl0539zl");
        user1.setFirstName("Lei");
        user1.setLastName("Zhu");

        User savedUser =repo.save(user1);

        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(existUser.getEmail()).isEqualTo(user1.getEmail());
    }

    @Test
    public void testFindUserByEmail(){
        String email = "zzzlll1995@gmail.com";

        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }
}
