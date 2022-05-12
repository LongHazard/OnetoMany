package com.example.spring2.repository;

import com.example.spring2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user_data u where u.role_od=?1",nativeQuery = true)
    List<User> findByidRole(int idRole);
}
