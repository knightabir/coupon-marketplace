/**
 * This class implements the methods declared on the SellerService interface.
 * It is responsible for handling all the operations related to the Seller entity.
 * It uses the SellerRepository to interact with the database.
 * The service methods are annotated with logs to track the operations done on the Seller entity.
 *
 * @author Abir Sarkar
 */
package com.marketplace.couponMarketplace.service.impl;

import com.marketplace.couponMarketplace.model.Seller;
import com.marketplace.couponMarketplace.repository.SellerRepository;
import com.marketplace.couponMarketplace.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller registerSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Optional<Seller> getSellerById(String sellerId) {
        return sellerRepository.findById(sellerId);
    }

    @Override
    public void updateWalletBalance(String sellerId, double amount) {
        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        if (seller != null) {
            seller.setWalletBalance(seller.getWalletBalance() + amount);
            sellerRepository.save(seller);
        }
    }
}