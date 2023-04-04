package com.phonecompany.billing;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator {

    @Override
    public BigDecimal calculate(String phoneLog) {
        String[] lines = phoneLog.split("\n");
        HashMap<String, PhoneNumber> phoneNumbers = calculateForEachNumber(lines);

        return sumAllPrices(phoneNumbers);
    }

    private HashMap<String, PhoneNumber> calculateForEachNumber(String[] lines) {
        HashMap<String, PhoneNumber> phoneNumbers = new HashMap<>();

        for (String currentLine : lines) {
            String[] fields = currentLine.split(",");
            String phoneNumberStr = fields[0];
            PhoneNumber currentPhoneNumber = phoneNumbers.get(phoneNumberStr);

            if (currentPhoneNumber == null) {
                currentPhoneNumber = new PhoneNumber(phoneNumberStr, 0.0, 0);
                phoneNumbers.put(phoneNumberStr, currentPhoneNumber);
            }

            updatePhoneNumberRecord(currentPhoneNumber, fields);
        }

        return phoneNumbers;
    }

    private void updatePhoneNumberRecord(PhoneNumber currentPhoneNumber, String[] fields) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(fields[1], formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(fields[2], formatter);

        Duration duration = Duration.between(startDateTime, endDateTime);
        int minutes = (int) Math.ceil(duration.getSeconds() / 60.0);

        double price = 0.0;

        for (LocalDateTime time = startDateTime;
             time.isBefore(endDateTime) && time.isBefore(startDateTime.plusMinutes(5));
             time = time.plusMinutes(1)) {

            if (time.getHour() >= 8 && time.getHour() < 16) {
                price += 1.0;
            }
            else {
                price += 0.5;
            }
        }

        if (minutes > 5) {
            price += (minutes - 5) * 0.2;
        }

        currentPhoneNumber.increasePrice(price);
        currentPhoneNumber.incrementNumOfCalls();
    }

    private BigDecimal sumAllPrices(HashMap<String, PhoneNumber> phoneNumbers) {
        PhoneNumber mostlyFrequentedNumber = null;
        int highestFrequency = 0;
        double totalPrice = 0.0;

        for(PhoneNumber currentPhoneNumber : phoneNumbers.values()) {
            totalPrice += currentPhoneNumber.getPrice();

            int currentNumOfCalls = currentPhoneNumber.getNumOfCalls();

            if (currentNumOfCalls > highestFrequency) {
                highestFrequency = currentNumOfCalls;
                mostlyFrequentedNumber = currentPhoneNumber;
            }
            else if (currentNumOfCalls == highestFrequency) {
                if (mostlyFrequentedNumber == null) {
                    mostlyFrequentedNumber = currentPhoneNumber;
                }

                mostlyFrequentedNumber = getNumberWithHigherDigitalSum(mostlyFrequentedNumber, currentPhoneNumber);
            }

        }

        if (mostlyFrequentedNumber != null) {
            totalPrice -= mostlyFrequentedNumber.getPrice();
        }

        return new BigDecimal(totalPrice);
    }

    private PhoneNumber getNumberWithHigherDigitalSum(PhoneNumber mostlyFrequentedNumber, PhoneNumber currentPhoneNumber) {
        int mostlyFrequentedNumberSum = mostlyFrequentedNumber.getPhoneNumber()
                .chars()
                .map(Character::getNumericValue)
                .sum();

        int currentPhoneNumberSum = currentPhoneNumber.getPhoneNumber()
                .chars()
                .map(Character::getNumericValue)
                .sum();

        if (mostlyFrequentedNumberSum > currentPhoneNumberSum) {
            return mostlyFrequentedNumber;
        }

        return currentPhoneNumber;
    }
}
