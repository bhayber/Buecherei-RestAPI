package de.adesso.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "demo_verlag")
public class Verlag extends EntityBase {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "adresse")
	private String adresse;

	@Column(name = "jahrlUmsatz")
	private double jahrlUmsatz;

	@OneToMany(mappedBy = "verlag")
	private Set<Book_Verlag> bookVerlag;

	public Set<Book_Verlag> getBookVerlag() {
		return bookVerlag;
	}

	public void setBookVerlag(Set<Book_Verlag> bookVerlag) {
		this.bookVerlag = bookVerlag;
	}

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