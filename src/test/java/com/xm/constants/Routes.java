package com.xm.constants;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Routes {

    private final String economicCalendar = "/research/economicCalendar";
    private final String riskPdf = "/assets/pdf/new/docs/XMGlobal-Risk-Disclosures-for-Financial-Instruments.pdf";
}
