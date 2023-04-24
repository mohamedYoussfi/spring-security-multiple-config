package net.youssfi.springsec.repository;

import net.youssfi.springsec.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,String> {
}
