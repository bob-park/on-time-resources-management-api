package com.malgn.adapter.web.api.v1.devices.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;

import com.malgn.application.devices.model.DeviceResult;
import com.malgn.domain.devices.DeviceStatus;
import com.malgn.domain.devices.DeviceType;

@Builder
public record DeviceResponseV1(String id,
                               String teamId,
                               DeviceType deviceType,
                               String name,
                               String description,
                               String model,
                               String manufacturer,
                               String serialNumber,
                               String os,
                               String osVersion,
                               String cpu,
                               DeviceStatus status,
                               LocalDateTime purchaseDate,
                               String ipAddress,
                               Map<String, Object> options,
                               LocalDateTime createdDate,
                               String createdBy,
                               LocalDateTime lastModifiedDate,
                               String lastModifiedBy) {

    public static DeviceResponseV1 from(DeviceResult result) {
        return DeviceResponseV1.builder()
            .id(String.valueOf(result.id()))
            .teamId(result.teamId() != null ? String.valueOf(result.teamId()) : null)
            .deviceType(result.deviceType())
            .name(result.name())
            .description(result.description())
            .model(result.model())
            .manufacturer(result.manufacturer())
            .serialNumber(result.serialNumber())
            .os(result.os())
            .osVersion(result.osVersion())
            .cpu(result.cpu())
            .status(result.status())
            .purchaseDate(result.purchaseDate())
            .ipAddress(result.ipAddress())
            .options(result.options())
            .createdDate(result.createdDate())
            .createdBy(result.createdBy())
            .lastModifiedDate(result.lastModifiedDate())
            .lastModifiedBy(result.lastModifiedBy())
            .build();
    }
}
