package com.demo.transactiondemo.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

//@Component
public class FirstProgrammaticApproach {
    PlatformTransactionManager transactionManager;

    public FirstProgrammaticApproach(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    public void updateUser(){
        TransactionStatus status = transactionManager.getTransaction(null);
        try{
            System.out.println("Do Operation");
            transactionManager.commit(status);
        }catch (Exception e){
            transactionManager.rollback(status);
        }
    }
}
