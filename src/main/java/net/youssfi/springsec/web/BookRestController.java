package net.youssfi.springsec.web;

import lombok.AllArgsConstructor;
import net.youssfi.springsec.entities.Book;
import net.youssfi.springsec.repository.BookRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BookRestController {
    private BookRepository bookRepository;
    @GetMapping("/books")
    @PreAuthorize("hasRole('USER')")
    public List<Book> bookList(){
        return bookRepository.findAll();
    }
    @PostMapping("/books")
    @PreAuthorize("hasRole('ADMIN')")
    public Book bookList(@RequestBody Book book){
        book.setId(UUID.randomUUID().toString());
        return bookRepository.save(book);
    }
}
