package com.malgn.adapter.web.api.v1.software.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;

import com.malgn.application.software.model.SoftwareResult;
import com.malgn.domain.software.SoftwarePlatform;
import com.malgn.domain.software.SoftwareStatus;

@Builder
public record SoftwareResponseV1(String id,
                                 String teamId,
                                 SoftwarePlatform platform,
                                 String name,
                                 String description,
                                 String manufacturer,
                                 String version,
                                 SoftwareStatus status,
                                 Map<String, Object> options,
                                 LocalDateTime purchaseDate,
                                 String licenseKey,
                                 LocalDateTime licenseStartDate,
                                 LocalDateTime licenseEndDate,
                                 LocalDateTime createdDate,
                                 String createdBy,
                                 LocalDateTime lastModifiedDate,
                                 String lastModifiedBy) {

    public static SoftwareResponseV1 from(SoftwareResult result) {
        return SoftwareResponseV1.builder()
            .id(String.valueOf(result.id()))
            .teamId(result.teamId() != null ? String.valueOf(result.teamId()) : null)
            .platform(result.platform())
            .name(result.name())
            .description(result.description())
            .manufacturer(result.manufacturer())
            .version(result.version())
            .status(result.status())
            .options(result.options())
            .purchaseDate(result.purchaseDate())
            .licenseKey(result.licenseKey())
            .licenseStartDate(result.licenseStartDate())
            .licenseEndDate(result.licenseEndDate())
            .createdDate(result.createdDate())
            .createdBy(result.createdBy())
            .lastModifiedDate(result.lastModifiedDate())
            .lastModifiedBy(result.lastModifiedBy())
            .build();
    }
}
