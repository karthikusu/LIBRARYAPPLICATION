package com.greatlearning.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.Entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsername(String s);

}
