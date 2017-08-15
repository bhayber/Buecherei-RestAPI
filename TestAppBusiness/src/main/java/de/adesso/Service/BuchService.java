package de.adesso.Service;

import de.adesso.Repository.BookRepository;
import de.adesso.Repository.VerlagRepository;
import de.adesso.model.Book;
import de.adesso.model.Book_Verlag;
import de.adesso.model.Verlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BuchService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private VerlagRepository verlagRepository;

    public BuchService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String createBuchWithVerlag(Book book, Verlag verlag) {
        Book newBook = new Book();
        try {
            newBook.setTitel(book.getTitel());
            newBook.setIsbnr(book.getIsbnr());
            Verlag v = new Verlag();
            v.setName(verlag.getName());
            v.setAdresse(verlag.getAdresse());

            Book_Verlag bv = new Book_Verlag();
            Set<Book_Verlag> book_verlags = new HashSet<Book_Verlag>();
            bv.setBook(newBook);
            bv.setVerlag(v);
            book_verlags.add(bv);
            newBook.setBookVerlag(book_verlags);
            verlagRepository.save(v);
            bookRepository.save(newBook);
        } catch (Exception ex) {
            return "Error creating the Book: " + ex.toString();
        }
        return "Book succesfully created with id = " + newBook.getId();
    }


    public String addVerlagToBook(Book book, Verlag verlag) {

        try {
            Book foundBook = bookRepository.findById(book.getId());
            Verlag foundVerlag = verlagRepository.findById(verlag.getId());

            Book_Verlag bv = new Book_Verlag();
            Set<Book_Verlag> book_verlags = new HashSet<Book_Verlag>();
            book_verlags.addAll(foundBook.getBookVerlag());
            bv.setBook(foundBook);
            bv.setVerlag(foundVerlag);
            book_verlags.add(bv);
            foundBook.setBookVerlag(book_verlags);
            bookRepository.save(foundBook);

        } catch (Exception ex) {
            return ex.toString();
        }
        return "Buch erfolgreich zugeordnet ! ";
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Set<Book> getAllBooksNotRentedByCustomer() {
        Set<Book> filtBookList = (Set<Book>) bookRepository.findAll();

        for (Book curBook:  filtBookList
             )
            if (curBook.getPerson() != null) {
                filtBookList.remove(curBook);
            }
            return filtBookList;
    }

    public Book getBookByID(String bookID){
    return bookRepository.findById(bookID);
    }

    public String createBuch(Book book, Verlag verlag) {
        Book newBook = new Book();
        Set<Book_Verlag> book_verlags = new HashSet<Book_Verlag>();
        try {
            newBook.setTitel(book.getTitel());
            newBook.setIsbnr(book.getIsbnr());
            Verlag v = verlagRepository.findVerlagByName(verlag.getName());
            Book_Verlag bv = new Book_Verlag();
            bv.setBook(newBook);
            bv.setVerlag(v);
            book_verlags.add(bv);
            newBook.setBookVerlag(book_verlags);
            verlagRepository.save(v);
            bookRepository.save(newBook);
        } catch (Exception ex) {
            return "Error creating the Book: " + ex.toString() + newBook.getId();
        }
        return "Book succesfully created with id = " + newBook.getId();
    }



}
