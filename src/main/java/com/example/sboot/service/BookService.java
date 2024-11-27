package com.example.sboot.service;

import com.example.sboot.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    //@Autowired
    private final BookRepository bookRepository;

}
