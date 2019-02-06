package de.adesso.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "demo_book")
public class Book extends EntityBase {

	private static final long serialVersionUID = -2988774184945522811L;

	@Column(name = "artNr", nullable = false, unique = true)
	private String articleNumber;

	@Column(name = "titel", nullable = false)
	private String titel;

	@Column(name = "isbnr", nullable = false)
	private String isbnr;

	@Column(name = "abgabeDatum")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date abgabeDatum;

	@Column(name = "ausleihDatum")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ausleihDatum;

	@Column(name = "abgabefristDatum")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date abgabefristDatum;

	@ManyToOne
	@JoinColumn(name = "FK_Kunde")
	@JsonBackReference // Damit es nicht zu einer Rückkoplung führt und eine unendliche JSON Datei
						// ausgegeben wird
	private Kunde kunde;

	@OneToOne
	@JoinColumn(name = "FK_GenreID")
	private StammBookGenre genre;

	@ManyToOne
	@JoinColumn(name = "FK_Verlag")
	private Verlag verlag;

	public StammBookGenre getGenre() {
		return genre;
	}

	public void setGenre(StammBookGenre genre) {
		this.genre = genre;
	}

	public Date getAbgabefristDatum() {
		return abgabefristDatum;
	}

	public void setAbgabefristDatum(Date abgabefristDatum) {
		this.abgabefristDatum = abgabefristDatum;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public String getIsbnr() {
		return isbnr;
	}

	public void setIsbnr(String isbnr) {
		this.isbnr = isbnr;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Date getAbgabeDatum() {
		return abgabeDatum;
	}

	public void setAbgabeDatum(Date abgabeDatum) {
		this.abgabeDatum = abgabeDatum;
	}

	public Date getAusleihDatum() {
		return ausleihDatum;
	}

	public void setAusleihDatum(Date ausleihDatum) {
		this.ausleihDatum = ausleihDatum;
	}

	/**
	 * @return the articleNumber
	 */
	public String getArticleNumber() {
		return articleNumber;
	}

	/**
	 * @param articleNumber the articleNumber to set
	 */
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	/**
	 * @return the verlag
	 */
	public Verlag getVerlag() {
		return verlag;
	}

	/**
	 * @param verlag the verlag to set
	 */
	public void setVerlag(Verlag verlag) {
		this.verlag = verlag;
	}

}
