package com.malgn.adapter.persistence.jpa.devices;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.malgn.application.devices.model.DeviceFindRequest;
import com.malgn.application.devices.required.DeviceRepository;
import com.malgn.domain.devices.Device;

@RequiredArgsConstructor
@Repository
public class JpaDeviceRepositoryAdaptor implements DeviceRepository {

    private final JpaDeviceRepository deviceRepository;

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Optional<Device> findById(Long id) {
        return deviceRepository.findById(id);
    }

    @Override
    public Page<Device> findDevices(DeviceFindRequest findRequest, Pageable pageable) {
        return deviceRepository.findDevices(findRequest, pageable);
    }
}
