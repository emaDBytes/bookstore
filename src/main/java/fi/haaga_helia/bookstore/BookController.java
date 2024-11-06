package fi.haaga_helia.bookstore;

import java.util.List;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import fi.haaga_helia.bookstore.model.Book;
import fi.haaga_helia.bookstore.repository.BookRepository;
import fi.haaga_helia.bookstore.repository.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Handles GET requests to the "/index" and "/" endpoints.
     * 
     * @return The name of the view to be rendered, in this case "index".
     */
    @GetMapping({ "/index", "/" })
    public String index() {
        return "index";
    }

    /**
     * Handles GET requests to "/booklist" and returns the view name "booklist".
     * Adds a list of all books from the repository to the model.
     *
     * @param model the model to which the list of books is added
     * @return the name of the view to be rendered, "booklist"
     */
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    /**
     * Handles GET requests to "/add" endpoint.
     * 
     * This method adds a new Book object and a list of all categories to the model.
     * It returns the view name "addbook" to be rendered.
     * 
     * @param model the Model object to which attributes are added
     * @return the name of the view to be rendered, "addbook"
     */
    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    /**
     * Handles the POST request to save a book.
     * 
     * @param book the Book object to be saved
     * @return a redirect string to the book list view
     */
    @PostMapping("/save")
    public String saveBook(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    /**
     * Handles the deletion of a book by its ID.
     * 
     * @param bookId the ID of the book to be deleted
     * @return a redirect to the book list page
     */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    /**
     * Handles GET requests to "/api/books" and returns a list of all books.
     * 
     * @return a list of all books in the repository
     */
    @GetMapping("/api/books")
    @ResponseBody
    public List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

    /**
     * Handles GET requests to retrieve a book by its ID.
     *
     * @param bookId the ID of the book to retrieve
     * @return the Book object if found, otherwise null
     */
    @GetMapping("/api/books/{id}")
    @ResponseBody
    public Book bookRest(@PathVariable("id") Long bookId) {
        return repository.findById(bookId).orElse(null);
    }

    /**
     * Handles GET requests to the "/login" endpoint.
     * 
     * @return The name of the view to be rendered, in this case "login".
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}