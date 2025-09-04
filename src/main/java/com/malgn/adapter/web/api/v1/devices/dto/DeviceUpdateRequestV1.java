package com.malgn.adapter.web.api.v1.devices.dto;

import jakarta.validation.constraints.NotNull;

import com.malgn.domain.devices.DeviceStatus;

public record DeviceUpdateRequestV1(@NotNull DeviceStatus status) {
}
