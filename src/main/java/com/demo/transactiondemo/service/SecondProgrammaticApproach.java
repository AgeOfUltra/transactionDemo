package com.demo.transactiondemo.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

//@Component
public class SecondProgrammaticApproach {
    TransactionTemplate transactionTemplate = new TransactionTemplate();

    public SecondProgrammaticApproach(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    public  void updateUser(){
        TransactionCallback<TransactionStatus> dbTaskOperation = (TransactionStatus status) ->{
            System.out.println("Do Operation");
            return status;
        };
        transactionTemplate.execute(dbTaskOperation);
    }
}
