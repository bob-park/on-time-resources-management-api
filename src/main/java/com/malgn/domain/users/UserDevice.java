package com.malgn.domain.users;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

import com.malgn.common.entity.annotation.SnowflakeIdGenerateValue;
import com.malgn.domain.devices.Device;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users_devices")
public class UserDevice {

    @Id
    @SnowflakeIdGenerateValue
    private Long id;

    private Long userId;

    @Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    @Builder
    private UserDevice(Long id, Long userId) {

        checkArgument(isNotEmpty(userId), "userId must be provided.");

        this.id = id;
        this.userId = userId;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
