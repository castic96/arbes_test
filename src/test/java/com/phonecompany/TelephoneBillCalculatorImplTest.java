package com.phonecompany;

import com.phonecompany.billing.TelephoneBillCalculatorImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class TelephoneBillCalculatorImplTest {

    @Test
    public void testCalculateOneNumber() {
        String input = "420774577453,13-01-2020 07:58:00,13-01-2020 08:07:59";
        BigDecimal expectedOutput = new BigDecimal("0");

        BigDecimal actualOutput = (new TelephoneBillCalculatorImpl()).calculate(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCalculateTwoNumbers() {
        String input =("420774577453,13-01-2020 07:58:00,13-01-2020 08:07:59\n" +
                "420772577453,13-01-2020 07:58:00,13-01-2020 08:07:59");
        BigDecimal expectedOutput = new BigDecimal("5");

        BigDecimal actualOutput = (new TelephoneBillCalculatorImpl()).calculate(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCalculateNumbersFromTask() {
        String input =("420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n");
        BigDecimal expectedOutput = new BigDecimal("6.20");

        BigDecimal actualOutput = (new TelephoneBillCalculatorImpl()).calculate(input);
        actualOutput = actualOutput.setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCalculateMoreNumbers() {
        String input =("420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420772577453,13-01-2020 07:58:00,13-01-2020 08:08:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00");
        BigDecimal expectedOutput = new BigDecimal("50");

        BigDecimal actualOutput = (new TelephoneBillCalculatorImpl()).calculate(input);

        assertEquals(expectedOutput, actualOutput);
    }
}



