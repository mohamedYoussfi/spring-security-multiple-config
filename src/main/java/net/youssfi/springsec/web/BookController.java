package net.youssfi.springsec.web;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.youssfi.springsec.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
@AllArgsConstructor
public class BookController {
    public BookRepository bookRepository;
    @GetMapping("/books")
    public String books(Model model){
        model.addAttribute("books",bookRepository.findAll());
        return "books";
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
