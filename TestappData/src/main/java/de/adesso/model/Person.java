package de.adesso.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import static javax.persistence.EnumType.ORDINAL;

@Entity
@Table(name="demo_person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person extends EntityBase implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Set<Book> books;

	@Column(name="name",nullable = false)
	private String name;
    @Column(name="email",nullable = false)
	private String email;
	@Column
	private String telmobile;
    @Column(name = "gebDatum",nullable = true)
    private Date gebDatum;
    @Column(name = "adresse")
    private String adresse;


    @Column
    @Enumerated(ORDINAL)
    private Geschlecht geschlecht;

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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
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
