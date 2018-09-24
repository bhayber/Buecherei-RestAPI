package de.adesso.model;

import static javax.persistence.EnumType.ORDINAL;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "demo_person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person extends EntityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column
	private String telmobile;

	@Column(name = "gebDatum", nullable = true)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date gebDatum;

	@Column(name = "adresse")
	private String adresse;

	@Column(name = "password")
	private String password;

	@Column
	@Enumerated(ORDINAL)
	private Geschlecht geschlecht;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person(Person person) {
		super();
		this.adresse = person.adresse;
		this.email = person.email;
		this.gebDatum = person.gebDatum;
		this.name = person.name;
		this.telmobile = person.telmobile;
	}

	public Person() {

	}

	public Date getGebDatum() {
		return gebDatum;
	}

	public void setGebDatum(Date gebDatum) {
		this.gebDatum = gebDatum;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelmobile() {
		return telmobile;
	}

	public void setTelmobile(String telmobile) {
		this.telmobile = telmobile;
	}

}
