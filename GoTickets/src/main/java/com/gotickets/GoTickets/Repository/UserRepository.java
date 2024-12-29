package com.gotickets.GoTickets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gotickets.GoTickets.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);
	User findByNameAndEmailAndMobile(String name,String email,String mobile);

	boolean existsByName(String name);
	boolean existsByEmail(String email);
}
