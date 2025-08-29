package com.malgn.application.devices.model;

import lombok.Builder;

import com.malgn.domain.devices.DeviceStatus;
import com.malgn.domain.devices.DeviceType;

@Builder
public record DeviceFindRequest(String name,
                                String description,
                                Long teamId,
                                DeviceType deviceType,
                                DeviceStatus status,
                                String model,
                                String manufacturer,
                                String serialNumber) {
}
