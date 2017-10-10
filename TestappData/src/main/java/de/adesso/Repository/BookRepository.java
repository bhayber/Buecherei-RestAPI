/*
* Data Access Object - DAO
* */


package de.adesso.Repository;

import de.adesso.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findById(String id);

    Book findByIsbnr(String isbnr);

    Book findByTitel(String titel);

    Book findByAbgabeDatum(Date abgabeDatum);

    Book findByAusleihDatum(Date ausleihDatum);

    @Query("select b from Book b where b.kunde IS NULL")
    Iterable<Book> findAllBooksNotRented();
}