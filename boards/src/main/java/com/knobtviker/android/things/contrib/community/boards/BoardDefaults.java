package com.knobtviker.android.things.contrib.community.boards;

import android.Manifest;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.google.android.things.pio.I2cDevice;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;


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
     * I2C address range supported
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntRange(from = I2C_MIN_ADDRESS, to = I2C_MAX_ADDRESS)
    public @interface I2CAddress {
    }

    private final static int I2C_MIN_ADDRESS = 0;
    private final static int I2C_MAX_ADDRESS = 127;

    /**
     * Return the default I2C bus for each board.
     * Or null if board is not supported.
     */
    @Nullable
    public static String defaultI2CBus() {
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

    /**
     * Returns a list of I2C buses on the board.
     */
    public static List<String> i2CBuses() {
        final PeripheralManager peripheralManager = PeripheralManager.getInstance();
        return peripheralManager.getI2cBusList();
    }

    /**
     * Returns a list of I2C all devices connected to all I2C buses on the board.
     */
    public static List<I2CDevice> i2CDevices() {
        final List<I2CDevice> devices = new ArrayList<>();

        i2CBuses()
            .forEach(
                i2CBus ->
                    IntStream
                        .range(I2C_MIN_ADDRESS, I2C_MAX_ADDRESS + 1) //1 is not a magic number, end is exclusive in range
                        .forEach(
                            address -> {
                                try {
                                    boolean isConnected = phantomWrite(i2CBus, address);
                                    if (isConnected) {
                                        devices.add(
                                            new I2CDevice(
                                                i2CBus,
                                                address,
                                                String.format(Locale.US, "0x%02X", address)
                                            )
                                        );
                                    }
                                } catch (IOException e) {
                                    Log.e(TAG, e.getMessage(), e);
                                }
                            }
                        )
            );

        return devices;
    }

    /**
     * Returns true or false if I2C device is connected on specific I2C bus on the board.
     * Throws exception if address is out of range.
     */
    public static boolean isI2CDeviceConnected(@NonNull final String bus, @I2CAddress final int address) throws IOException {
        if (address >= I2C_MIN_ADDRESS && address <= I2C_MAX_ADDRESS) {
            return phantomWrite(bus, address);
        } else {
            throw new IOException("I2C address out of range.");
        }
    }

    @RequiresPermission("com.google.android.things.permission.USE_PERIPHERAL_IO")
    private static boolean phantomWrite(@NonNull final String bus, @I2CAddress final int address) throws IOException {
        final PeripheralManager peripheralManager = PeripheralManager.getInstance();
        final I2cDevice i2cDevice = peripheralManager.openI2cDevice(bus, address);
        boolean isConnected = false;
        try {
            i2cDevice.write(new byte[]{0x00}, 1);
            isConnected = true;
        } catch (IOException e) {
            isConnected = false;
        } finally {
            i2cDevice.close();
        }
        return isConnected;
    }
}
