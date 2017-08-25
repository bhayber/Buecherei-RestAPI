package de.adesso.Controller;

import de.adesso.Service.BuchService;
import de.adesso.model.Book;
import de.adesso.model.Verlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buch")
public class BuchController {

    @Autowired
    private BuchService buchService;

    @PostMapping("/createBuchAndVerlag")
    @ResponseBody
    public String createBuchAndVerlag(Book buch, Verlag verlag) {
        return buchService.createBuchWithVerlag(buch, verlag);
    }

    @PostMapping("/createBuch")
    @ResponseBody
    public String createBuch(Book buch) {
        return buchService.createBuch(buch);
    }

    @PostMapping("/createBuchAndAssignVerlag")
    @ResponseBody
    public String createBuchAndAssignVerlag(Book buch, Verlag verlag) {
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
    public String addVerlagToBook(Book book, Verlag verlag) {
        return buchService.addVerlagToBook(book, verlag);
    }


}
