package com.knobtviker.android.things.contrib.community.boards.models.devices;

import android.support.annotation.NonNull;

import com.knobtviker.android.things.contrib.community.boards.models.base.PortDevice;

public class I2CDevice implements PortDevice {

    public I2CDevice(@NonNull final String bus, final int address, @NonNull final String addressHex) {
        this.bus = bus;
        this.address = address;
        this.addressHex = addressHex;
    }

    public String bus() {
        return bus;
    }

    public int address() {
        return address;
    }

    public String addressHex() {
        return addressHex;
    }

    private String bus;

    private int address;

    private String addressHex;
}
