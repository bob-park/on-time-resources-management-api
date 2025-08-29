package com.malgn.application.users.provided;

import java.util.List;

import com.malgn.application.devices.model.DeviceResult;

public interface UserDeviceFinder {

    List<DeviceResult> getUserDevices(Long userId);

}
