package com.malgn.application.devices.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.application.devices.model.DeviceFindRequest;
import com.malgn.application.devices.model.DeviceResult;
import com.malgn.application.devices.provided.DeviceFinder;
import com.malgn.application.devices.required.DeviceRepository;
import com.malgn.domain.devices.Device;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DeviceQueryService implements DeviceFinder {

    private final DeviceRepository deviceRepository;

    @Override
    public Page<DeviceResult> devices(DeviceFindRequest findRequest, Pageable pageable) {

        Page<Device> result = deviceRepository.findDevices(findRequest, pageable);

        return result.map(DeviceResult::from);
    }
}
