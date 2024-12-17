package com.marketplace.couponMarketplace.service;

import com.marketplace.couponMarketplace.model.Seller;

import java.util.Optional;
/**
 * This interface declares the methods that will be implemented by the Seller service.
 * The Seller service is responsible for handling all the operations related to the Seller entity.
 * It provides methods for registering a new Seller, retrieving a Seller by its id, and updating the wallet balance of a Seller.
 *
 * @author Abir Sarkar
 */

public interface SellerService {
    Seller registerSeller(Seller seller);
    Optional<Seller> getSellerById(String sellerId);
    void updateWalletBalance(String sellerId, double amount); }

