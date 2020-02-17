package com.example.ims.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);

//    @Query("SELECT u FROM user u LIMIT :pageno, :size")
//    List<User> getUserListPaginated(@Param("pageno") int pageNo, @Param("size") int pageSize);
}
