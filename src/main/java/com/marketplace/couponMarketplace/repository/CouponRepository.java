package com.marketplace.couponMarketplace.repository;

import com.marketplace.couponMarketplace.model.Coupon;
import com.marketplace.couponMarketplace.model.CouponStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CouponRepository extends MongoRepository<Coupon,String> {
    List<Coupon> findBySellerId(String sellerId);
    List<Coupon> findByStatus(CouponStatus status);
}
