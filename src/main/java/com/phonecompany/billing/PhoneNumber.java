package com.phonecompany.billing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PhoneNumber {
    private String phoneNumber;

    private double price;

    private int numOfCalls;

    public void incrementNumOfCalls() {
        numOfCalls++;
    }

    public void increasePrice(double price) {
        this.price += price;
    }

}
