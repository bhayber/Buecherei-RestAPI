package de.adesso.Controller;

import de.adesso.Service.BuchService;
import de.adesso.Service.PersonService;
import de.adesso.model.Book;
import de.adesso.model.Geschlecht;
import de.adesso.model.Person;
import de.adesso.model.Verlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/buch")
public class BuchController {
	  
	  @Autowired
	  private BuchService buchService;

	  @RequestMapping("/createBuchWithVerlag")
	  @ResponseBody
	  public String createBuchWithVerlag(Book buch, Verlag verlag) {
	    return buchService.createBuchWithVerlag(buch,verlag);
	  }

    @PostMapping("/createBuch")
    @ResponseBody
    public String createBuch(Book buch, Verlag verlag) {
        return buchService.createBuch(buch,verlag);
    }

    /**
     * GET /all  --> Gets all Person Information
     */
    @GetMapping("/getall")
    @ResponseBody
    public Iterable<Book> getAllBooks() {
        return buchService.getAllBooks();
    }

    /**
     * GET /all  --> Gets all Books which are not Rented yet
     */
    @GetMapping("/getAllBooksNotRented")
    @ResponseBody
    public Iterable<Book> getAllBooksNotRentedByCustomer() {
        return buchService.getAllBooksNotRentedByCustomer();
    }

}
