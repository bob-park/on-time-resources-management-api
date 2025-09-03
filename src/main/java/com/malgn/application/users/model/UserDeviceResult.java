package com.malgn.application.users.model;

import lombok.Builder;

import com.malgn.domain.users.UserDevice;

@Builder
public record UserDeviceResult(Long id,
                               Long userId) {

    public static UserDeviceResult from(UserDevice userDevice) {
        return UserDeviceResult.builder()
            .id(userDevice.getId())
            .userId(userDevice.getUserId())
            .build();
    }

}
