package de.mygroup.powercore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mygroup.powercore.model.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByNameAndPassword(String name, String password);

	User findByName(String name);

}
