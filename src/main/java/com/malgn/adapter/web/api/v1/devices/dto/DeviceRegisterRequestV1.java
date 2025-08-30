package com.malgn.adapter.web.api.v1.devices.dto;

import java.time.LocalDate;
import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.malgn.domain.devices.DeviceType;

public record DeviceRegisterRequestV1(Long teamId,
                                      @NotNull DeviceType deviceType,
                                      @NotEmpty String name,
                                      String description,
                                      @NotEmpty String model,
                                      @NotEmpty String manufacturer,
                                      String serialNumber,
                                      String os,
                                      String osVersion,
                                      String cpu,
                                      Long memory,
                                      Long storage,
                                      LocalDate purchaseDate,
                                      String ipAddress,
                                      Map<String, Object> options) {
}
