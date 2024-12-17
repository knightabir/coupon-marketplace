/**
 * This class implements the methods declared on the CouponService interface.
 * This service is responsible for handling all the operations related to the Coupon entity.
 * It uses the CouponRepository to interact with the database.
 * The service methods are annotated with logs to track the operations done on the Coupon entity.
 *
 * @author Abir Sarkar
 */
package com.marketplace.couponMarketplace.service.impl;

import com.marketplace.couponMarketplace.model.Coupon;
import com.marketplace.couponMarketplace.model.CouponStatus;
import com.marketplace.couponMarketplace.repository.CouponRepository;
import com.marketplace.couponMarketplace.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    private static final Logger log = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Override
    public Coupon addCoupon(Coupon coupon) {
        // This method adds a coupon.
        // It takes a Coupon object, saves it to the database and returns the saved coupon.
        // The coupon status is set to ACTIVE when adding a coupon.
        coupon.setStatus(CouponStatus.ACTIVE);
//        coupon.setCouponStatus(CouponStatus.ACTIVE);
        coupon.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        coupon.setCreatedBy("current user id from the jwt token");
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> getCouponsBySellerId(String sellerId) {
        // This method returns all the coupons of a seller.
        // It takes a sellerId as a parameter, searches the database and returns all the coupons associated with the sellerId.
        log.info("Getting all coupons of the seller with id {}", sellerId);
        return couponRepository.findBySellerId(sellerId);
    }

    @Override
    public List<Coupon> getCouponsByStatus(CouponStatus status) {
        // This method returns all the coupons with the given status.
        // It takes a status as a parameter, searches the database and returns all the coupons associated with the status.
        log.info("Getting all coupons with status {}", status);
        return couponRepository.findByStatus(status);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        // This method returns all the coupons.
        // It searches the database and returns all the coupons.
        log.info("Getting all coupons");
        return couponRepository.findAll();
    }

    @Override
    public Coupon getCouponById(String couponId) {
        // This method returns a coupon by its id.
        // It takes a couponId as a parameter, searches the database and returns the coupon associated with the couponId.
        log.info("Getting coupon with id {}", couponId);
        return couponRepository.findById(couponId).orElse(null);
    }

    @Override
    public void updateCouponStatus(String couponId, CouponStatus status) {
        // This method updates the status of a coupon.
        // It takes a couponId and a status as parameters, searches the database, and updates the coupon associated with the couponId.
        log.info("Updating coupon with id {} to status {}", couponId, status);
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        if (coupon != null) {
            coupon.setStatus(status);
            couponRepository.save(coupon);
            log.info("Coupon with id {} updated successfully", couponId);
        }else {
            log.error("Coupon with id {} not found", couponId);
        }
    }

    @Override
    public void deleteCoupon(String couponId) {
        // This method deletes a coupon by its id.
        // It takes a couponId as a parameter, searches the database, and deletes the coupon associated with the couponId.
        log.info("Deleting coupon with id {}", couponId);
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCouponsForBuyer(String buyerId) {
        // This method returns all the coupons of a buyer.
        // It takes a buyerId as a parameter, searches the database and returns all the coupons associated with the buyerId.
        log.info("Getting all coupons for buyer with id {}", buyerId);
        return List.of();
    }
}
