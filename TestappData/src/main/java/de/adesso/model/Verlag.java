package de.adesso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "demo_verlag")
public class Verlag extends EntityBase {

	private static final long serialVersionUID = 67877853931313942L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "adresse")
	private String adresse;

	@Column(name = "jahrlUmsatz")
	private double jahrlUmsatz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public double getJahrlUmsatz() {
		return jahrlUmsatz;
	}

	public void setJahrlUmsatz(double jahrlUmsatz) {
		this.jahrlUmsatz = jahrlUmsatz;
	}
}