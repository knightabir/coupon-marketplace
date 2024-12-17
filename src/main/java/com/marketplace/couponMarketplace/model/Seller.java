package com.marketplace.couponMarketplace.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Getter
@Data
@EqualsAndHashCode(callSuper = true)
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User{
    private double walletBalance;
    private int rating;


    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public int getRating() {
        return rating;
    }
}
