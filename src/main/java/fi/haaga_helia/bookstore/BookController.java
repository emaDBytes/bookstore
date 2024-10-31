package fi.haaga_helia.bookstore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import fi.haaga_helia.bookstore.model.Book;
import fi.haaga_helia.bookstore.model.Category;
import fi.haaga_helia.bookstore.repository.BookRepository;
import fi.haaga_helia.bookstore.repository.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    // Original index endpoint
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // List all books
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // Add new book (show form)
    @GetMapping("/add") 
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    // Save new book
    @PostMapping("/save")
    public String saveBook(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    // Delete book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }
 
    // REST endpoint to get all books
    @GetMapping("/api/books")
    @ResponseBody
    public List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

}