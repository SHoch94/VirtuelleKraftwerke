package de.mygroup.powercore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mygroup.powercore.model.Powerplant;
import de.mygroup.powercore.model.Type;
import de.mygroup.powercore.model.VirtualPowerplant;

public interface PowerplantDao extends JpaRepository<Powerplant, Long> {

	List<Powerplant> findAllByType(Type type);

	List<Powerplant> findAllByVirtualPowerplant(VirtualPowerplant vPp);
}
