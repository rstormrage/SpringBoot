package com.elective.repository;

import com.elective.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("from User where number = ?1 and deleted = false" )
    User findByNumber(String number);

//    @Query(value = "select * from User where number = ?1 and is_deleted = false", nativeQuery = true)
//    User findByNumber(String number);
//
//    User findByNumberAndDeletedIs(String number, boolean deleted);
}
