package com.malgn.application.devices.required;

import java.util.Optional;

import com.malgn.domain.devices.Device;

public interface DeviceRepository {

    Device save(Device device);

    Optional<Device> findById(Long id);

}
