package com.knobtviker.android.things.contrib.community.boards.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
    PWMPrimary.UNKNOWN,
    PWMPrimary.PWM0,
    PWMPrimary.PWM1,
    PWMPrimary.PWM7
})
public @interface PWMPrimary {
    String UNKNOWN = "";
    String PWM0 = "PWM0";
    String PWM1 = "PWM1";
    String PWM7 = "PWM7";
}
