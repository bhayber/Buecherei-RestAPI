package de.adesso.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "demo_book_verlag")
public class Book_Verlag implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date verDatum;

	@Id
	@ManyToOne
	@JoinColumn(name = "fk_book", referencedColumnName = "id")
	private Book book;

	@Id
	@ManyToOne
	@JoinColumn(name = "fk_verlag", referencedColumnName = "id")
	private Verlag verlag;

	public Date getVerDatum() {
		return verDatum;
	}

	public void setVerDatum(Date verDatum) {
		this.verDatum = verDatum;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Verlag getVerlag() {
		return verlag;
	}

	public void setVerlag(Verlag verlag) {
		this.verlag = verlag;
	}

}
