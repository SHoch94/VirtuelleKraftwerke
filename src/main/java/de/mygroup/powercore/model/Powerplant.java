package de.mygroup.powercore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Powerplant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "typeId")
	private Type type;
	private double powerConversion;
	@Lob
	private byte[] image;
	@ManyToOne
	@JoinColumn(name = "virtualPPId")
	private VirtualPowerplant virtualPowerplant;
	private Date acquisition;
	private String manufacturer;
	private double price;
	private String location;
	private long runtimeInHours;

	public double getPowerConversion() {
		return powerConversion;
	}

	public void setPowerConversion(double powerConversion) {
		this.powerConversion = powerConversion;
	}

	public Date getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(Date acquisition) {
		this.acquisition = acquisition;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getRuntimeInHours() {
		return runtimeInHours;
	}

	public void setRuntimeInHours(long runtimeInHours) {
		this.runtimeInHours = runtimeInHours;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public VirtualPowerplant getVirtualPowerplant() {
		return virtualPowerplant;
	}

	public void setVirtualPowerplant(VirtualPowerplant virtualPowerplant) {
		this.virtualPowerplant = virtualPowerplant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
