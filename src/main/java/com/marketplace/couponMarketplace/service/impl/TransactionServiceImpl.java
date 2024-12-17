/**
 * This class implements the methods declared on the TransactionService interface.
 * This service is responsible for handling all the operations related to the Transaction entity.
 * It uses the TransactionRepository to interact with the database.
 * The service methods are annotated with logs to track the operations done on the Transaction entity.
 * 
 * @author Abir Sarkar
 */
package com.marketplace.couponMarketplace.service.impl;

import com.marketplace.couponMarketplace.model.Transaction;
import com.marketplace.couponMarketplace.repository.TransactionRepository;
import com.marketplace.couponMarketplace.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setTransactionTime(new java.sql.Timestamp(System.currentTimeMillis()));
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByBuyerId(String buyerId) {
        return transactionRepository.findByBuyerId(buyerId);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(String sellerId) {
        return transactionRepository.findBySellerId(sellerId);
    }
}