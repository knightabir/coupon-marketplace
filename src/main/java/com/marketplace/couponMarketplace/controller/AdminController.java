package com.marketplace.couponMarketplace.controller;

import com.marketplace.couponMarketplace.model.Coupon;
import com.marketplace.couponMarketplace.model.Transaction;
import com.marketplace.couponMarketplace.model.User;
import com.marketplace.couponMarketplace.service.CouponService;
import com.marketplace.couponMarketplace.service.TransactionService;
import com.marketplace.couponMarketplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // get all the sellers
//    @GetMapping("/sellers")
//    public List<User> getAllSellers() {
//        return userService.getAllSellers();
//    }
//
//    // get all the buyers
//    @GetMapping("/buyers")
//    public List<User> getAllBuyers() {
//        return userService.getAllBuyers();
//    }

    // Delete user by id
    @DeleteMapping("/users/{id}")
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }

    // ManageCoupons
    @DeleteMapping("/coupons/{id}")
    public void deleteCoupon(String id) {
        couponService.deleteCoupon(id);
    }

    @GetMapping("/coupons")
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

//    @GetMapping("/transactions")
//    public List<Transaction> getAllTransactions() {
//        return transactionService.getAllTransactions();
//    }

    @GetMapping("/transactionsByBuyerId/{buyerId}")
    public List<Transaction> getTransactionsByBuyerId(String buyerId) {
        return transactionService.getTransactionsByBuyerId(buyerId);
    }

    @GetMapping("/transactionsBySellerId/{sellerId}")
    public List<Transaction> getTransactionsBySellerId(String sellerId) {
        return transactionService.getTransactionsBySellerId(sellerId);
    }
}
