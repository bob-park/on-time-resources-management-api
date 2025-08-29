package com.malgn.application.users.provided;

import com.malgn.application.devices.model.DeviceResult;
import com.malgn.application.users.model.DeviceProvideRequest;
import com.malgn.common.model.Id;
import com.malgn.domain.devices.Device;

public interface UserDeviceProvider {

    DeviceResult provide(Long userId, Id<Device, Long> deviceId, DeviceProvideRequest provideRequest);

}
