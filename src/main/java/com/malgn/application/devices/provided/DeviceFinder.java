package com.malgn.application.devices.provided;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.malgn.application.devices.model.DeviceFindRequest;
import com.malgn.application.devices.model.DeviceResult;
import com.malgn.common.model.Id;
import com.malgn.domain.devices.Device;

public interface DeviceFinder {

    Page<DeviceResult> devices(DeviceFindRequest findRequest, Pageable pageable);

    DeviceResult device(Id<Device, Long> id);

}
