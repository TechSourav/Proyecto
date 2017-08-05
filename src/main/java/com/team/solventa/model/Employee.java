package com.team.solventa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	private String name;
	private String surnames;
	private String gender;
	private String mobilePhone;
	private String email;
	/*
	 * TODO Jhonny: investigar cual es el tipo de dato adecuado para 
	 * representar fechas, tanto en java, como en la base de datos 
	 * quevayamos a utilizar.
	 */
	private String birthdate;
	private String address;
	private String town;
	private String zipCode;
	private String hourlyRate;
	private String Availability;

	/*
	 * TODO Jhonny: hacer análisis datos que faltes. Por ejemplo, una 
	 * lista de pueblos donde el empleado quiera trabajar o dar la
	 * posibilidad de que se conviera en autónomo (tipo Wayook.es).
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getAvailability() {
		return Availability;
	}

	public void setAvailability(String availability) {
		Availability = availability;
	}

}
