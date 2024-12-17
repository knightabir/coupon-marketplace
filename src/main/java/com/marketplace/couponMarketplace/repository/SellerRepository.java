package com.marketplace.couponMarketplace.repository;

import com.marketplace.couponMarketplace.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller,String> {
}
