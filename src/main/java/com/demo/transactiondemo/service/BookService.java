package com.demo.transactiondemo.service;

import com.demo.transactiondemo.entity.Book;
import com.demo.transactiondemo.respository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;



//    @Transactional(transactionManager = "transactionManager")
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public Book saveBook(Book book){
       return bookRepository.save(book);
    }
}
