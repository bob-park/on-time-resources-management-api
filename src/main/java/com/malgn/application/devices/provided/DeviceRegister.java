package com.malgn.application.devices.provided;

import com.malgn.application.devices.model.DeviceRegisterRequest;
import com.malgn.application.devices.model.DeviceResult;
import com.malgn.application.devices.model.DeviceUpdateRequest;
import com.malgn.common.model.Id;
import com.malgn.domain.devices.Device;

public interface DeviceRegister {

    DeviceResult register(DeviceRegisterRequest registerRequest);

    DeviceResult update(Id<Device, Long> id, DeviceUpdateRequest updateRequest);

}
