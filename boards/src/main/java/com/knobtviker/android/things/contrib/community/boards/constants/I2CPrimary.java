package com.knobtviker.android.things.contrib.community.boards.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
    I2CPrimary.UNKNOWN,
    I2CPrimary.I2C1,
    I2CPrimary.I2C2
})
public @interface I2CPrimary {
    String UNKNOWN = "";
    String I2C1 = "I2C1";
    String I2C2 = "I2C2";
}
