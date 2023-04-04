package com.phonecompany;

import com.phonecompany.billing.TelephoneBillCalculator;
import com.phonecompany.billing.TelephoneBillCalculatorImpl;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
public class App
{
    public static void main( String[] args )
    {
        log.info(LocalDateTime.now() + ": Application started.");

        //Sample code
        TelephoneBillCalculator telephoneBillCalculator = new TelephoneBillCalculatorImpl();

        String input = """
                420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00
                420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00
                420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00
                420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00
                420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00
                420774577453,13-01-2020 07:58:00,13-01-2020 08:08:00
                420772577453,13-01-2020 07:58:00,13-01-2020 08:08:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00
                420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00""";

        BigDecimal result = telephoneBillCalculator.calculate(input);

        System.out.println("Result: " + result);

        log.info(LocalDateTime.now() + ": Application finished.");
    }
}
