package com.malgn.application.users.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.application.devices.model.DeviceResult;
import com.malgn.application.users.provided.UserDeviceFinder;
import com.malgn.application.users.required.UserDeviceRepository;
import com.malgn.domain.users.UserDevice;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserDeviceQueryService implements UserDeviceFinder {

    private final UserDeviceRepository userDeviceRepository;

    @Override
    public List<DeviceResult> getUserDevices(Long userId) {

        List<UserDevice> result = userDeviceRepository.userDevices(userId);

        return result.stream()
            .map(item -> DeviceResult.from(item.getDevice()))
            .toList();
    }
}
