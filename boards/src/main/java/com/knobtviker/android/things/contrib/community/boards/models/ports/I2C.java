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

    public static I2C NONE = create("", Collections.emptyList());

    public static I2C create(@NonNull final String primary, @NonNull final List<String> all) {
        return new I2C(primary, all);
    }

    private I2C(@NonNull final String primary, @NonNull final List<String> all) {
        this.primary = primary;
        this.all = all;
    }

    @Override
    public String primary() {
        return primary;
    }

    @Override
    public List<String> all() {
        return all;
    }
}
