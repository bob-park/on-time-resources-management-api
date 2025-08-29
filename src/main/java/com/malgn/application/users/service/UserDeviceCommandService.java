package com.malgn.application.users.service;

import static com.malgn.application.devices.model.DeviceResult.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.application.devices.model.DeviceResult;
import com.malgn.application.devices.required.DeviceRepository;
import com.malgn.application.users.model.DeviceProvideRequest;
import com.malgn.application.users.provided.UserDeviceProvider;
import com.malgn.common.exception.NotFoundException;
import com.malgn.common.model.Id;
import com.malgn.domain.devices.Device;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserDeviceCommandService implements UserDeviceProvider {

    private final DeviceRepository deviceRepository;

    @Override
    public DeviceResult provide(Long userId, Id<Device, Long> deviceId, DeviceProvideRequest provideRequest) {

        Device device =
            deviceRepository.findById(deviceId.getValue())
                .orElseThrow(() -> new NotFoundException(deviceId));

        device.provideToUser(userId, provideRequest.startDate(), provideRequest.endDate());

        log.debug("provided device. ({})", device);

        return from(device);
    }
}
