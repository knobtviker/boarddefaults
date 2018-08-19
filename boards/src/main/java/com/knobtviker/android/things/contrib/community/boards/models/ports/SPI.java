package com.knobtviker.android.things.contrib.community.boards.models.ports;

import android.support.annotation.NonNull;

import com.knobtviker.android.things.contrib.community.boards.models.base.Port;

import java.util.Collections;
import java.util.List;

public class SPI implements Port {

    @NonNull
    private final String primary;

    @NonNull
    private final List<String> all;

    public static SPI NONE = create("", Collections.emptyList());

    public static SPI create(@NonNull final String primary, @NonNull final List<String> all) {
        return new SPI(primary, all);
    }

    private SPI(@NonNull final String primary, @NonNull final List<String> all) {
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
