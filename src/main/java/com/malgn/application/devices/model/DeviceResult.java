package com.malgn.application.devices.model;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;

import com.malgn.domain.devices.Device;
import com.malgn.domain.devices.DeviceStatus;
import com.malgn.domain.devices.DeviceType;

@Builder
public record DeviceResult(Long id,
                           Long teamId,
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

    public static DeviceResult from(Device device) {
        return DeviceResult.builder()
            .id(device.getId())
            .teamId(device.getTeamId())
            .deviceType(device.getDeviceType())
            .name(device.getName())
            .description(device.getDescription())
            .model(device.getModel())
            .manufacturer(device.getManufacturer())
            .serialNumber(device.getSerialNumber())
            .os(device.getOs())
            .osVersion(device.getOsVersion())
            .cpu(device.getCpu())
            .status(device.getStatus())
            .purchaseDate(device.getPurchaseDate())
            .ipAddress(device.getIpAddress())
            .options(device.getOptions())
            .createdDate(device.getCreatedDate())
            .createdBy(device.getCreatedBy())
            .lastModifiedDate(device.getLastModifiedDate())
            .lastModifiedBy(device.getLastModifiedBy())
            .build();
    }
}
