package com.knobtviker.android.things.contrib.community.boards;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.google.android.things.pio.I2cDevice;
import com.google.android.things.pio.PeripheralManager;
import com.knobtviker.android.things.contrib.community.boards.constants.Board;
import com.knobtviker.android.things.contrib.community.boards.constants.I2CAddress;
import com.knobtviker.android.things.contrib.community.boards.constants.I2CPrimary;
import com.knobtviker.android.things.contrib.community.boards.constants.PWMPrimary;
import com.knobtviker.android.things.contrib.community.boards.constants.SPIPrimary;
import com.knobtviker.android.things.contrib.community.boards.constants.UARTPrimary;
import com.knobtviker.android.things.contrib.community.boards.models.Device;
import com.knobtviker.android.things.contrib.community.boards.models.devices.I2CDevice;
import com.knobtviker.android.things.contrib.community.boards.models.ports.I2C;
import com.knobtviker.android.things.contrib.community.boards.models.ports.PWM;
import com.knobtviker.android.things.contrib.community.boards.models.ports.SPI;
import com.knobtviker.android.things.contrib.community.boards.models.ports.UART;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.IntStream;

import timber.log.Timber;

/**
 * Created by bojan on 21/02/2018.
 */

public class BoardDefaults {

    private static Optional<BoardDefaults> INSTANCE = Optional.empty();

    @NonNull
    private PeripheralManager peripheralManager;

    @NonNull
    private Device device;

    private static BoardDefaults create() {
        if (!INSTANCE.isPresent()) {
            INSTANCE = Optional.of(new BoardDefaults());
        }
        return INSTANCE.get();
    }

    public static Device build() {
        return create().device();
    }

    private BoardDefaults() {
        this.peripheralManager = PeripheralManager.getInstance();
        this.device = device();
    }

    private Device device() {
        switch (Build.DEVICE) {
            case Board.RPI3:
                return Device.create(
                    "Raspberry",
                    "Pi 3",
                    I2C.create(primaryI2C(), allI2C()),
                    SPI.create(primarySPI(), allSPI()),
                    PWM.create(primaryPWM(), allPWM()),
                    UART.create(primaryUART(), allUART())
                );
            case Board.IMX6UL_PICO:
                return Device.create(
                    "Pico Pi",
                    "i.MX6UL",
                    I2C.create(primaryI2C(), allI2C()),
                    SPI.create(primarySPI(), allSPI()),
                    PWM.create(primaryPWM(), allPWM()),
                    UART.create(primaryUART(), allUART())
                );
            case Board.IMX7D_PICO:
                return Device.create(
                    "Pico Pi",
                    "i.MX7D",
                    I2C.create(primaryI2C(), allI2C()),
                    SPI.create(primarySPI(), allSPI()),
                    PWM.create(primaryPWM(), allPWM()),
                    UART.create(primaryUART(), allUART())
                );
            default:
                return Device.UNKNOWN;
        }
    }
    /**
     * Return the default I2C bus for each board.
     */
    private String primaryI2C() {
        switch (Build.DEVICE) {
            case Board.RPI3:
                return I2CPrimary.I2C1;
            case Board.IMX6UL_PICO:
                return I2CPrimary.I2C2;
            case Board.IMX7D_PICO:
                return I2CPrimary.I2C1;
            default:
                return I2CPrimary.UNKNOWN;
        }
    }

    /**
     * Returns a list of I2C buses on the board.
     */
    private List<String> allI2C() {
        return peripheralManager.getI2cBusList();
    }

    /**
     * Return the preferred SPI port for each board.
     */
    private String primarySPI() {
        switch (Build.DEVICE) {
            case Board.RPI3:
                return SPIPrimary.SPI0_0;
            case Board.IMX6UL_PICO:
                return SPIPrimary.SPI3_0;
            case Board.IMX7D_PICO:
                return SPIPrimary.SPI3_1;
            default:
                return SPIPrimary.UNKNOWN;
        }
    }

    /**
     * Returns a list of SPI buses on the board.
     */
    private List<String> allSPI() {
        return peripheralManager.getSpiBusList();
    }

    /**
     * Return the PWM for current board.
     */
    private String primaryPWM() {
        switch (Build.DEVICE) {
            case Board.RPI3:
                return PWMPrimary.PWM0;
            case Board.IMX6UL_PICO:
                return PWMPrimary.PWM7;
            case Board.IMX7D_PICO:
                return PWMPrimary.PWM1;
            default:
                return PWMPrimary.UNKNOWN;
        }
    }

    /**
     * Returns a list of PWM pins on the board.
     */
    private List<String> allPWM() {
        return peripheralManager.getPwmList();
    }

    /**
     * Return the UART for current board.
     */
    private String primaryUART() {
        switch (Build.DEVICE) {
            case Board.RPI3:
                return UARTPrimary.UART0;
            case Board.IMX6UL_PICO:
                return UARTPrimary.UART3;
            case Board.IMX7D_PICO:
                return UARTPrimary.UART6;
            default:
                return UARTPrimary.UNKNOWN;
        }
    }

    /**
     * Returns a list of UART devices on the board.
     */
    private List<String> allUART() {
        return peripheralManager.getUartDeviceList();
    }

    /**
     * Returns a list of I2C all devices connected to all I2C buses on the board.
     */
    public static List<I2CDevice> devicesI2C() {
        final List<I2CDevice> devices = new ArrayList<>();

        final PeripheralManager peripheralManager = PeripheralManager.getInstance();
        peripheralManager.getI2cBusList()
            .forEach(
                i2CBus ->
                    IntStream
                        .range(I2CAddress.MIN, I2CAddress.MAX + 1) //1 is not a magic number, end is exclusive in range
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
                                    Timber.e(e);
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
    private boolean isI2CDeviceConnected(@NonNull final String bus, @I2CAddress final int address) throws IOException {
        if (address >= I2CAddress.MIN && address <= I2CAddress.MAX) {
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
