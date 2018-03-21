package com.knobtviker.android.things.contrib.community.boards;

import android.support.annotation.NonNull;

public class I2CDevice {

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
