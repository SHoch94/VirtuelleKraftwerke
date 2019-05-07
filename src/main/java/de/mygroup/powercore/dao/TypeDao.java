package de.mygroup.powercore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mygroup.powercore.model.Type;

public interface TypeDao extends JpaRepository<Type, Long> {

}
