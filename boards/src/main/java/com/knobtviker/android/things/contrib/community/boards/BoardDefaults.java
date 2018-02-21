package com.knobtviker.android.things.contrib.community.boards;

import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by bojan on 21/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public class BoardDefaults {
    private static final String TAG = BoardDefaults.class.getSimpleName();

    private static final String DEVICE_RPI3 = "rpi3";
    private static final String DEVICE_IMX6UL_PICO = "imx6ul_pico";
    private static final String DEVICE_IMX7D_PICO = "imx7d_pico";

    /**
     * Return the preferred I2C port for each board.
     * Or null if board is not supported.
     */
    @Nullable
    public static String getI2CPort() {
        switch (Build.DEVICE) {
            case DEVICE_RPI3:
                return "I2C1";
            case DEVICE_IMX6UL_PICO:
                return "I2C2";
            case DEVICE_IMX7D_PICO:
                return "I2C1";
            default:
                Log.e(TAG, String.format("Unknown Build.DEVICE %s", Build.DEVICE));
                return null;
        }
    }
}
