package com.knobtviker.android.things.contrib.community.boards.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
    SPIPrimary.UNKNOWN,
    SPIPrimary.SPI0_0,
    SPIPrimary.SPI3_0,
    SPIPrimary.SPI3_1
})
public @interface SPIPrimary {
    String UNKNOWN = "";
    String SPI0_0 = "SPI0.0";
    String SPI3_0 = "SPI3.0";
    String SPI3_1 = "SPI3.1";
}
