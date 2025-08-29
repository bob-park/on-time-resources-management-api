package com.malgn.application.devices.model;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;

import com.malgn.domain.devices.DeviceType;

@Builder
public record DeviceRegisterRequest(Long teamId,
                                    DeviceType deviceType,
                                    String name,
                                    String description,
                                    String model,
                                    String manufacturer,
                                    String serialNumber,
                                    String os,
                                    String osVersion,
                                    String cpu,
                                    LocalDateTime purchaseDate,
                                    String ipAddress,
                                    Map<String, Object> options) {
}
