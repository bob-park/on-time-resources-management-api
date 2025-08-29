package com.malgn.application.software.model;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;

import com.malgn.domain.software.Software;
import com.malgn.domain.software.SoftwarePlatform;
import com.malgn.domain.software.SoftwareStatus;

@Builder
public record SoftwareResult(Long id,
                             Long teamId,
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

    public static SoftwareResult from(Software software) {
        return SoftwareResult.builder()
            .id(software.getId())
            .teamId(software.getTeamId())
            .platform(software.getPlatform())
            .name(software.getName())
            .description(software.getDescription())
            .manufacturer(software.getManufacturer())
            .version(software.getVersion())
            .status(software.getStatus())
            .options(software.getOptions())
            .purchaseDate(software.getPurchaseDate())
            .licenseKey(software.getLicenseKey())
            .licenseStartDate(software.getLicenseStartDate())
            .licenseEndDate(software.getLicenseEndDate())
            .createdDate(software.getCreatedDate())
            .createdBy(software.getCreatedBy())
            .lastModifiedDate(software.getLastModifiedDate())
            .lastModifiedBy(software.getLastModifiedBy())
            .build();
    }
}
