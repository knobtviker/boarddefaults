package com.knobtviker.android.things.contrib.community.boards.models.ports;

import android.support.annotation.NonNull;

import com.knobtviker.android.things.contrib.community.boards.models.devices.I2CDevice;
import com.knobtviker.android.things.contrib.community.boards.models.base.Port;

import java.util.Collections;
import java.util.List;

public class I2C implements Port {

    @NonNull
    private final String primary;

    @NonNull
    private final List<String> all;

    @NonNull
    private final List<I2CDevice> devices;

    public static I2C NONE = create("", Collections.emptyList(), Collections.emptyList());

    public static I2C create(@NonNull final String primary, @NonNull final List<String> all, @NonNull final List<I2CDevice> devices) {
        return new I2C(primary, all, devices);
    }

    private I2C(@NonNull final String primary, @NonNull final List<String> all, @NonNull final List<I2CDevice> devices) {
        this.primary = primary;
        this.all = all;
        this.devices = devices;
    }

    @Override
    public String primary() {
        return primary;
    }

    @Override
    public List<String> all() {
        return all;
    }

    @Override
    public List<I2CDevice> devices() {
        return devices;
    }
}
