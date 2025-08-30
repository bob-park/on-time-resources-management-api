package com.malgn.application.devices.service;

import static com.malgn.application.devices.model.DeviceResult.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.application.devices.model.DeviceRegisterRequest;
import com.malgn.application.devices.model.DeviceResult;
import com.malgn.application.devices.provided.DeviceRegister;
import com.malgn.application.devices.required.DeviceRepository;
import com.malgn.domain.devices.Device;
import com.malgn.domain.devices.DeviceStatus;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class DeviceCommandService implements DeviceRegister {

    private final DeviceRepository deviceRepository;

    @Override
    public DeviceResult register(DeviceRegisterRequest registerRequest) {

        Device createdDevice =
            Device.builder()
                .teamId(registerRequest.teamId())
                .deviceType(registerRequest.deviceType())
                .name(registerRequest.name())
                .description(registerRequest.description())
                .model(registerRequest.model())
                .manufacturer(registerRequest.manufacturer())
                .serialNumber(registerRequest.serialNumber())
                .os(registerRequest.os())
                .osVersion(registerRequest.osVersion())
                .cpu(registerRequest.cpu())
                .memory(registerRequest.memory())
                .storage(registerRequest.storage())
                .status(DeviceStatus.WAITING)
                .purchaseDate(registerRequest.purchaseDate())
                .ipAddress(registerRequest.ipAddress())
                .options(registerRequest.options())
                .build();

        createdDevice = deviceRepository.save(createdDevice);

        log.debug("registered device. ({})", createdDevice);

        return from(createdDevice);
    }
}
