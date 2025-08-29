package com.malgn.adapter.web.api.v1.software.dto;

import java.time.LocalDate;
import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.malgn.domain.software.SoftwarePlatform;

public record SoftwareRegisterRequestV1(@NotNull Long teamId,
                                        @NotNull SoftwarePlatform platform,
                                        @NotEmpty String name,
                                        String description,
                                        @NotEmpty String manufacturer,
                                        String version,
                                        Map<String, Object> options,
                                        LocalDate purchaseDate,
                                        String licenseKey,
                                        LocalDate licenseStartDate,
                                        LocalDate licenseEndDate) {
}
