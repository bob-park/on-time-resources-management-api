package com.malgn.domain.software;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.*;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import com.malgn.common.entity.BaseEntity;
import com.malgn.common.entity.annotation.SnowflakeIdGenerateValue;
import com.malgn.common.entity.converter.MapJsonConverter;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "software")
public class Software extends BaseEntity<Long> {

    @Id
    @SnowflakeIdGenerateValue
    private Long id;

    private Long teamId;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private SoftwarePlatform platform;

    private String manufacturer;
    private String version;

    @Enumerated(EnumType.STRING)
    private SoftwareStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Convert(converter = MapJsonConverter.class)
    private Map<String, Object> options = new HashMap<>();

    private LocalDateTime purchaseDate;
    private String licenseKey;
    private LocalDateTime licenseStartDate;
    private LocalDateTime licenseEndDate;

    @Builder
    private Software(Long id, Long teamId, String name, String description, SoftwarePlatform platform,
        String manufacturer, String version, SoftwareStatus status, Map<String, Object> options,
        LocalDateTime purchaseDate, String licenseKey, LocalDateTime licenseStartDate, LocalDateTime licenseEndDate) {

        checkArgument(isNotBlank(name), "name must be provided.");
        checkArgument(isNotEmpty(platform), "platform must be provided.");
        checkArgument(isNotBlank(manufacturer), "manufacturer must be provided.");
        checkArgument(isNotEmpty(purchaseDate), "purchaseDate must be provided.");

        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.description = description;
        this.platform = platform;
        this.manufacturer = manufacturer;
        this.version = version;
        this.status = defaultIfNull(status, SoftwareStatus.WAITING);
        this.options = options;
        this.purchaseDate = purchaseDate;
        this.licenseKey = licenseKey;
        this.licenseStartDate = licenseStartDate;
        this.licenseEndDate = licenseEndDate;
    }

    /*
     * 편의 메서드
     */
}
