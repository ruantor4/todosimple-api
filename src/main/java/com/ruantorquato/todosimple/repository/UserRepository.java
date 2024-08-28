package com.ruantorquato.todosimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruantorquato.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
