package de.adesso.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "demo_StammBookGenre")
public class StammBookGenre extends EntityBase {

	private static final long serialVersionUID = -3820065532724559323L;

	@Column(name = "bezeichnung", nullable = false)
	private String bezeichnung;

	@OneToMany(mappedBy = "genre")
	private List<Book> books;

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}
