package de.adesso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "demo_book")
public class Book extends EntityBase {

    @Column(name = "titel", nullable = false)
    private String titel;

    @Column(name = "isbnr", nullable = false)
    private String isbnr;

    @Column(name = "abgabeDatum")
    private Date abgabeDatum;

    @Column(name = "ausleihDatum")
    private Date ausleihDatum;

    @Column(name = "abgabefristDatum")
    private Date abgabefristDatum;

    @ManyToOne
    @JoinColumn(name = "FK_Kunde")
    @JsonBackReference // Damit es nicht zu einer Rückkoplung führt und eine unendliche JSON Datei ausgegeben wird
    private Kunde kunde;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book_Verlag> bookVerlag;

    public Date getAbgabefristDatum() {
        return abgabefristDatum;
    }

    public void setAbgabefristDatum(Date abgabefristDatum) {
        this.abgabefristDatum = abgabefristDatum;
    }

    public Set<Book_Verlag> getBookVerlag() {
        return bookVerlag;
    }

    public void setBookVerlag(Set<Book_Verlag> bookVerlag) {
        this.bookVerlag = bookVerlag;
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

}
