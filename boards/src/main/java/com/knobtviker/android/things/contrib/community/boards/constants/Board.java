package com.knobtviker.android.things.contrib.community.boards.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
    Board.RPI3,
    Board.IMX6UL_PICO,
    Board.IMX7D_PICO
})
public @interface Board {
    String RPI3 = "rpi3";
    String IMX6UL_PICO = "imx6ul_pico";
    String IMX7D_PICO = "imx7d_pico";
}
