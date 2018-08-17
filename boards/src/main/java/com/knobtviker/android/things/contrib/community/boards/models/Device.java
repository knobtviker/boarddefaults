package com.knobtviker.android.things.contrib.community.boards.models;

import android.support.annotation.NonNull;

import com.knobtviker.android.things.contrib.community.boards.models.ports.I2C;
import com.knobtviker.android.things.contrib.community.boards.models.ports.PWM;
import com.knobtviker.android.things.contrib.community.boards.models.ports.SPI;
import com.knobtviker.android.things.contrib.community.boards.models.ports.UART;

public class Device {

    @NonNull
    private final String manufacturer;

    @NonNull
    private final String model;

    @NonNull
    private final I2C i2c;

    @NonNull
    private final SPI spi;

    @NonNull
    private final PWM pwm;

    @NonNull
    private final UART uart;

    public static Device UNKNOWN = Device.create("", "", I2C.NONE, SPI.NONE, PWM.NONE, UART.NONE);

    public static Device create(@NonNull final String manufacturer, @NonNull final String model,
        @NonNull final I2C i2c, @NonNull final SPI spi, @NonNull final PWM pwm, @NonNull final UART uart) {
        return new Device(manufacturer, model, i2c, spi, pwm, uart);
    }

    private Device(@NonNull final String manufacturer, @NonNull final String model,
        @NonNull final I2C i2c, @NonNull final SPI spi, @NonNull final PWM pwm, @NonNull final UART uart) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.i2c = i2c;
        this.spi = spi;
        this.pwm = pwm;
        this.uart = uart;
    }

    @NonNull
    public String manufacturer() {
        return manufacturer;
    }

    @NonNull
    public String model() {
        return model;
    }

    @NonNull
    public I2C I2C() {
        return i2c;
    }

    @NonNull
    public SPI SPI() {
        return spi;
    }

    @NonNull
    public PWM PWM() {
        return pwm;
    }

    @NonNull
    public UART UART() {
        return uart;
    }
}
