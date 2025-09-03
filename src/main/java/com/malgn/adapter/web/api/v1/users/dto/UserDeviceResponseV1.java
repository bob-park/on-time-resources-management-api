package com.malgn.adapter.web.api.v1.users.dto;

import lombok.Builder;

import com.malgn.application.users.model.UserDeviceResult;

@Builder
public record UserDeviceResponseV1(String id,
                                   String userId) {

    public static UserDeviceResponseV1 from(UserDeviceResult result) {
        return UserDeviceResponseV1.builder()
            .id(String.valueOf(result.id()))
            .userId(String.valueOf(result.userId()))
            .build();
    }

}
