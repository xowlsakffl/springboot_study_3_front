package com.example.thymeleaf.repository;

import com.example.thymeleaf.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                                             // Entity(table), PK
public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository에서 제공해주는 기본 CRUD Method

}
