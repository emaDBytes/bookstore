package fi.haaga_helia.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import fi.haaga_helia.bookstore.repository.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
}