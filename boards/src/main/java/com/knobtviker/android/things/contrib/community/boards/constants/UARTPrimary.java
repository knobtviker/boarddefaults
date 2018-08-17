package com.knobtviker.android.things.contrib.community.boards.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
    UARTPrimary.UNKNOWN,
    UARTPrimary.UART0,
    UARTPrimary.UART3,
    UARTPrimary.UART6
})
public @interface UARTPrimary {
    String UNKNOWN = "";
    String UART0 = "UART0";
    String UART3 = "UART3";
    String UART6 = "UART6";
}
