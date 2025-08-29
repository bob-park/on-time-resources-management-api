package com.malgn.application.devices.provided;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.malgn.application.devices.model.DeviceFindRequest;
import com.malgn.application.devices.model.DeviceResult;

public interface DeviceFinder {

    Page<DeviceResult> devices(DeviceFindRequest findRequest, Pageable pageable);

}
