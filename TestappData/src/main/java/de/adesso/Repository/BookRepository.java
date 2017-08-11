package de.adesso.Repository;

import de.adesso.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public Book findById(String id);

    public Book findByIsbnr(String isbnr);

    public Book findByTitel(String titel);

    public Book findByAbgabeDatum(Date abgabeDatum);

    public Book findByAusleihDatum(Date ausleihDatum);


}