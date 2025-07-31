package com.demo.transactiondemo.controller;

import com.demo.transactiondemo.entity.Book;
import com.demo.transactiondemo.service.BookService;
import com.demo.transactiondemo.service.FirstProgrammaticApproach;
import com.demo.transactiondemo.service.SecondProgrammaticApproach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService service;

//    @Autowired
//    FirstProgrammaticApproach firstProgrammaticApproach;
//
//    @Autowired
//    SecondProgrammaticApproach secondProgrammaticApproach;

    @PostMapping("/book/save")
//    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
//        secondProgrammaticApproach.updateUser();
//        firstProgrammaticApproach.updateUser();
        Book info = service.saveBook(book);
        return ResponseEntity.ok().body(info);
    }

}
