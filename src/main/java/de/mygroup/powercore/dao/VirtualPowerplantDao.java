package de.mygroup.powercore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mygroup.powercore.model.VirtualPowerplant;

public interface VirtualPowerplantDao extends JpaRepository<VirtualPowerplant, Long> {
}
