package de.adesso.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.adesso.Repository.BookRepository;
import de.adesso.Repository.VerlagRepository;
import de.adesso.model.Book;
import de.adesso.model.Kunde;
import de.adesso.model.Verlag;

@Service
public class BuchService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private VerlagRepository verlagRepository;

	public BuchService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public String createBuchWithVerlag(Book book, Verlag verlag) {
		try {
			verlagRepository.save(verlag);
			bookRepository.save(book);
		} catch (Exception ex) {
			return "Error creating the Book: " + ex.toString();
		}
		return "Book succesfully created with id = " + book.getId();
	}

	public String addVerlagToBook(Book book, Verlag verlag) {

		try {
			Book foundBook = bookRepository.findById(book.getId());
			Verlag foundVerlag = verlagRepository.findById(verlag.getId());
			foundBook.setVerlag(foundVerlag);
			bookRepository.save(foundBook);
		} catch (Exception ex) {
			return ex.toString();
		}
		return "Verlag erfolgreich zugeordnet ! ";
	}

	public String customerRentTheBook(Book book, Kunde kunde) {
		try {
			Book foundBook = bookRepository.findById(book.getId());
			foundBook.setKunde(kunde);
			bookRepository.save(foundBook);
		} catch (Exception ex) {
			return ex.toString();
		}
		return "Erfolg";
	}

	public Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public ArrayList<Book> getAllBooksNotRentedByCustomer() {
		return (ArrayList<Book>) bookRepository.findAllBooksNotRented();
	}

	public Book getBookByID(String bookID) {
		return bookRepository.findById(bookID);
	}

	public String createBuch(Book book, Verlag verlag) {
		Book newBook = new Book();
		try {
			newBook.setTitel(book.getTitel());
			newBook.setIsbnr(book.getIsbnr());
			Verlag v = verlagRepository.findVerlagByName(verlag.getName());
			verlagRepository.save(v);
			bookRepository.save(newBook);
		} catch (Exception ex) {
			return "Error creating the Book: " + ex.toString() + newBook.getId();
		}
		return "Book succesfully created with id = " + newBook.getId();
	}

	public String createBuch(Book book) {
		try {
			bookRepository.save(book);
		} catch (Exception ex) {
			return "Error creating the Book: " + ex.toString();
		}
		return "Book succesfully created with id = " + book.getId();
	}
}
