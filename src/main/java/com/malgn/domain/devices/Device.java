package com.malgn.domain.devices;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.*;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonType;

import com.malgn.common.entity.BaseEntity;
import com.malgn.common.entity.annotation.SnowflakeIdGenerateValue;
import com.malgn.common.entity.converter.MapJsonConverter;
import com.malgn.domain.users.UserDevice;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "devices")
public class Device extends BaseEntity<Long> {

    @Id
    @SnowflakeIdGenerateValue
    private Long id;

    private Long teamId;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    private String name;
    private String description;

    private String model;
    private String manufacturer;
    private String serialNumber;
    private String os;
    private String osVersion;
    private String cpu;
    private Long memory;
    private Long storage;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    private LocalDateTime purchaseDate;
    private String ipAddress;

    @Type(JsonType.class)
    @Convert(converter = MapJsonConverter.class)
    private Map<String, Object> options = new HashMap<>();

    @Exclude
    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDevice userDevice;

    @Exclude
    @OrderBy("createdDate asc")
    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceHistory> deviceHistories = new ArrayList<>();

    @Builder
    private Device(Long id, Long teamId, DeviceType deviceType, String name, String description, String model,
        String manufacturer, String serialNumber, String os, String osVersion, String cpu, Long memory, Long storage,
        DeviceStatus status, LocalDateTime purchaseDate, String ipAddress, Map<String, Object> options) {

        checkArgument(isNotEmpty(deviceType), "deviceType must be provided");
        checkArgument(isNotBlank(name), "name must be provided");
        checkArgument(isNotBlank(model), "model must be provided");
        checkArgument(isNotBlank(manufacturer), "manufacturer must be provided");

        this.id = id;
        this.teamId = teamId;
        this.deviceType = deviceType;
        this.name = name;
        this.description = description;
        this.model = model;
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
        this.os = os;
        this.osVersion = osVersion;
        this.cpu = cpu;
        this.memory = memory;
        this.storage = storage;
        this.status = status;
        this.purchaseDate = defaultIfNull(purchaseDate, LocalDateTime.now());
        this.ipAddress = ipAddress;
        this.options = defaultIfNull(options, Map.of());
    }

    /*
     * 편의 메서드
     */
    public void updateStatus(DeviceStatus status) {
        this.status = status;

        String reason = "";

        switch (status) {
            case LOST -> {
                reason = "분실";
            }
            case BROKEN -> {
                reason = "고장";
            }
            case REPAIRING -> {
                reason = "수리";
            }
            case WAITING -> {
                this.userDevice = null;
                reason = "대기";
            }
            case EXPORT -> {
                // 분출 처리
                reason = "분출";
            }
            default -> {
                // ignore
            }
        }

        DeviceHistory createdHistory =
            DeviceHistory.builder()
                .historyType(status)
                .userId(getUserDevice() != null ? getUserDevice().getUserId() : null)
                .reason(reason)
                .startDate(LocalDateTime.now())
                .build();

        addDeviceHistory(createdHistory);

    }

    public void provideToUser(Long userId, LocalDateTime startDate, LocalDateTime endDate) {

        checkArgument(isNotEmpty(startDate), "startDate must be provided.");

        this.status = DeviceStatus.USED;

        DeviceHistory createdHistory =
            DeviceHistory.builder()
                .historyType(DeviceStatus.USED)
                .userId(userId)
                .reason("사용 지급")
                .startDate(startDate)
                .endDate(endDate)
                .build();

        addDeviceHistory(createdHistory);

        UserDevice createdUserDevice =
            UserDevice.builder()
                .userId(userId)
                .build();

        createdUserDevice.setDevice(this);

        this.userDevice = createdUserDevice;

    }

    public void addDeviceHistory(DeviceHistory deviceHistory) {

        deviceHistory.setDevice(this);

        getDeviceHistories().add(deviceHistory);
    }
}
