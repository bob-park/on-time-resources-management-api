package com.malgn.domain.devices;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.*;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

import com.malgn.common.entity.BaseEntity;
import com.malgn.common.entity.annotation.SnowflakeIdGenerateValue;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "devices_histories")
public class DeviceHistory extends BaseEntity<Long> {

    @Id
    @SnowflakeIdGenerateValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeviceStatus historyType;

    private Long userId;

    @Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    private String reason;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder
    private DeviceHistory(Long id, DeviceStatus historyType, Long userId, String reason, LocalDateTime startDate,
        LocalDateTime endDate) {

        checkArgument(isNotEmpty(historyType), "historyType must be provided.");
        checkArgument(isNotBlank(reason), "reason must be provided.");
        checkArgument(isNotEmpty(startDate), "startDate must be provided.");

        this.id = id;
        this.historyType = historyType;
        this.userId = userId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /*
     * 편의 메서드
     */
    public void setDevice(Device device) {
        this.device = device;
    }
}
