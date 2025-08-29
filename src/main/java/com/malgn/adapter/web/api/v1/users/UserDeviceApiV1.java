package com.malgn.adapter.web.api.v1.users;

import static com.malgn.adapter.web.api.v1.devices.dto.DeviceResponseV1.*;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.adapter.web.api.v1.devices.dto.DeviceResponseV1;
import com.malgn.adapter.web.api.v1.users.dto.DeviceProvideRequestV1;
import com.malgn.application.devices.model.DeviceResult;
import com.malgn.application.users.model.DeviceProvideRequest;
import com.malgn.application.users.provided.UserDeviceProvider;
import com.malgn.common.model.Id;
import com.malgn.domain.devices.Device;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users/{userId:\\d+}/devices")
public class UserDeviceApiV1 {

    private final UserDeviceProvider userDeviceProvider;

    @PostMapping(path = "{deviceId:\\d+}")
    public DeviceResponseV1 provide(@PathVariable long userId, @PathVariable long deviceId,
        @RequestBody @Valid DeviceProvideRequestV1 provideRequest) {

        DeviceResult result =
            userDeviceProvider.provide(
                userId,
                Id.of(Device.class, deviceId),
                DeviceProvideRequest.builder()
                    .startDate(provideRequest.startDate().atStartOfDay())
                    .endDate(provideRequest.endDate() != null ? provideRequest.endDate().atStartOfDay() : null)
                    .build());

        return from(result);
    }

}
