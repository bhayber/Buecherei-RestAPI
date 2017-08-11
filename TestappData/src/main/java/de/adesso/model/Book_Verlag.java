package de.adesso.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "demo_book_verlag")
public class Book_Verlag implements Serializable {

    @Column(name = "verDatum")
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
