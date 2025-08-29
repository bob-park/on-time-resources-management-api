package com.malgn.application.devices.provided;

import com.malgn.application.devices.model.DeviceRegisterRequest;
import com.malgn.application.devices.model.DeviceResult;

public interface DeviceRegister {

    DeviceResult register(DeviceRegisterRequest registerRequest);

}
