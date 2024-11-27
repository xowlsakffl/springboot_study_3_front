package com.example.thymeleaf.service;

import com.example.thymeleaf.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    //@Autowired
    private final BookRepository bookRepository;

}
