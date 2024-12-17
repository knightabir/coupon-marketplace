package com.marketplace.couponMarketplace.service;

import com.marketplace.couponMarketplace.model.Coupon;
import com.marketplace.couponMarketplace.model.CouponStatus;

import java.util.List;

public interface CouponService {
    Coupon addCoupon(Coupon coupon);
    List<Coupon> getCouponsBySellerId(String sellerId);
    List<Coupon> getCouponsByStatus(CouponStatus status);
    List<Coupon> getAllCoupons();
    Coupon getCouponById(String couponId);
    void updateCouponStatus(String couponId, CouponStatus status);
    void deleteCoupon(String couponId);
    List<Coupon> getCouponsForBuyer(String buyerId);
}
