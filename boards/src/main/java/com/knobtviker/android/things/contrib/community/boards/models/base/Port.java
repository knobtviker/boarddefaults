package com.knobtviker.android.things.contrib.community.boards.models.base;

import com.knobtviker.android.things.contrib.community.boards.models.devices.I2CDevice;

import java.util.List;

public interface Port {

    String primary();

    List<String> all();

    List<I2CDevice> devices();
}
