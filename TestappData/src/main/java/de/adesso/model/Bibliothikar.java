package de.adesso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "demo_bibliothikar")
public class Bibliothikar extends Person {

	private static final long serialVersionUID = 1L;

	@Column(name = "objektAdresse")
	private String objektAdresse;

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "bereich")
	private Bereich bereich;

	public String getObjektAdresse() {
		return objektAdresse;
	}

	public void setObjektAdresse(String objektadresse) {
		this.objektAdresse = objektadresse;
	}

	public Bereich getBereich() {
		return bereich;
	}

	public void setBereich(Bereich bereich) {
		this.bereich = bereich;
	}

}
