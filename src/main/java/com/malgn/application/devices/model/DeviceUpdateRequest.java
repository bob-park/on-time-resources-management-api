package com.malgn.application.devices.model;

import lombok.Builder;

import com.malgn.domain.devices.DeviceStatus;

@Builder
public record DeviceUpdateRequest(DeviceStatus status) {
}
