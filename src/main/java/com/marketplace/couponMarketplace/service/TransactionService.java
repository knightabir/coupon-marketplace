package com.marketplace.couponMarketplace.service;

import com.marketplace.couponMarketplace.model.Transaction;

import java.util.List;

/**
 * This interface declares the methods that will be implemented by the Transaction service.
 * The Transaction service is responsible for handling all the operations related to the Transaction entity.
 * It provides methods for creating a new Transaction, retrieving Transactions by buyerId and sellerId.
 *
 * @author Abir Sarkar
 */
public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getTransactionsByBuyerId(String buyerId);
    List<Transaction> getTransactionsBySellerId(String sellerId);
}
