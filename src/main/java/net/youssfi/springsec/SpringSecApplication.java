package net.youssfi.springsec;

import net.youssfi.springsec.entities.Book;
import net.youssfi.springsec.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringSecApplication {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    public static void main(String[] args) {
        SpringApplication.run(SpringSecApplication.class, args);
    }

    @EventListener
    public void handle(ApplicationStartedEvent event){
        Stream.of("XML","Java","Oracle",".Net","Spring","UML","design Patterns")
                .forEach(title->{
                    Book book = Book.builder()
                            .id(UUID.randomUUID().toString()).title(title).build();
                    bookRepository.save(book);
                });
    }
    @EventListener
    public void handleJdbcAuth(ApplicationStartedEvent event){
        PasswordEncoder passwordEncoder=passwordEncoder();
       jdbcUserDetailsManager.createUser(User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build());
        jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
