package de.adesso.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "demo_Kunde")
public class Kunde extends Person {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kunde")
    private Set<Book> books;

    //BÜCHEREI AUSWEIS NR
    @Column(name = "ausweisnr", nullable = false, unique = true)
    private String ausweisnr;

    @Column(name = "mitgliedSeit")
    private Date mitgliedSeit;

    @Column(name = "austritt")
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
