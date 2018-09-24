package de.adesso.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "demo_Kunde")
public class Kunde extends Person {

	private static final long serialVersionUID = 5720520422383180131L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kunde")
	private Set<Book> books;

	// BÜCHEREI AUSWEIS NR
	@Column(name = "ausweisnr", nullable = false, unique = true)
	private String ausweisnr;

	@Column(name = "mitgliedSeit")
	@JsonFormat(pattern = "dd/MM/yyyy")
	// Allows dd/MM/yyyy date to be passed into GET request in JSON
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date mitgliedSeit;

	@Column(name = "austritt")
	@JsonFormat(pattern = "dd/MM/yyyy")
	// Allows dd/MM/yyyy date to be passed into GET request in JSON
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date austritt;

	public String getAusweisnr() {
		return ausweisnr;
	}

	public void setAusweisnr(String ausweisnr) {
		this.ausweisnr = ausweisnr;
	}

	public Date getMitgliedSeit() {
		return mitgliedSeit;
	}

	public void setMitgliedSeit(Date mitgliedSeit) {
		this.mitgliedSeit = mitgliedSeit;
	}

	public Date getAustritt() {
		return austritt;
	}

	public void setAustritt(Date austritt) {
		this.austritt = austritt;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
