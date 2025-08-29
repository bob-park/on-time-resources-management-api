package com.malgn.adapter.web.api.v1.devices.dto;

import com.malgn.domain.devices.DeviceStatus;
import com.malgn.domain.devices.DeviceType;

public record DeviceSearchRequestV1(String name,
                                    String description,
                                    Long teamId,
                                    DeviceType deviceType,
                                    DeviceStatus status,
                                    String model,
                                    String manufacturer,
                                    String serialNumber) {
}
