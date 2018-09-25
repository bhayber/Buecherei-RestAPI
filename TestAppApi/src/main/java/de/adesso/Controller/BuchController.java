package de.adesso.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.adesso.Service.BuchService;
import de.adesso.model.Book;
import de.adesso.model.Kunde;
import de.adesso.model.Verlag;

@RestController
@RequestMapping("/buch")
public class BuchController {

	@Autowired
	private BuchService buchService;

	@PostMapping("/createBuchAndVerlag")
	public String createBuchAndVerlag(@RequestBody Book buch, @RequestBody Verlag verlag) {
		return buchService.createBuchWithVerlag(buch, verlag);
	}

	@PutMapping("/userRentTheBook")
	public String userRentTheBook(@RequestBody Book book, @RequestBody Kunde kunde) {
		return buchService.customerRentTheBook(book, kunde);
	}

	@PostMapping("/createBuch")
	public String createBuch(Book buch) {
		return buchService.createBuch(buch);
	}

	@PostMapping("/createBuchAndAssignVerlag")
	@ResponseBody
	public String createBuchAndAssignVerlag(@RequestBody Book buch, @RequestBody Verlag verlag) {
		return buchService.createBuch(buch, verlag);
	}

	@GetMapping("/getall")
	@ResponseBody
	public Iterable<Book> getAllBooks() {
		return buchService.getAllBooks();
	}

	@GetMapping("/getAllBooksNotRented")
	@ResponseBody
	public Iterable<Book> getAllBooksNotRentedByCustomer() {
		return buchService.getAllBooksNotRentedByCustomer();
	}

	@PostMapping("/addVerlagToBook")
	@ResponseBody
	public String addVerlagToBook(@RequestBody Book book, @RequestBody Verlag verlag) {
		return buchService.addVerlagToBook(book, verlag);
	}

}
