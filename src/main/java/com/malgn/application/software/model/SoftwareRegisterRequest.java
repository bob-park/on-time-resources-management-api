package com.malgn.application.software.model;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;

import com.malgn.domain.software.SoftwarePlatform;

@Builder
public record SoftwareRegisterRequest(Long teamId,
                                      SoftwarePlatform platform,
                                      String name,
                                      String description,
                                      String manufacturer,
                                      String version,
                                      Map<String, Object> options,
                                      LocalDateTime purchaseDate,
                                      String licenseKey,
                                      LocalDateTime licenseStartDate,
                                      LocalDateTime licenseEndDate) {
}
