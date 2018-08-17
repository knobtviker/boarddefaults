package com.knobtviker.android.things.contrib.community.boards.constants;

import android.support.annotation.IntRange;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.knobtviker.android.things.contrib.community.boards.constants.I2CAddress.MAX;
import static com.knobtviker.android.things.contrib.community.boards.constants.I2CAddress.MIN;

@Retention(RetentionPolicy.SOURCE)
@IntRange(from = MIN, to = MAX)
public @interface I2CAddress {

    int MIN = 0;
    int MAX = 127;
}