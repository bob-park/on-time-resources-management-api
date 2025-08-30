package com.malgn.adapter.web.api.v1.devices;

import static com.malgn.adapter.web.api.v1.devices.dto.DeviceResponseV1.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.LocalDate;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.adapter.web.api.v1.devices.dto.DeviceRegisterRequestV1;
import com.malgn.adapter.web.api.v1.devices.dto.DeviceResponseV1;
import com.malgn.adapter.web.api.v1.devices.dto.DeviceSearchRequestV1;
import com.malgn.application.devices.model.DeviceFindRequest;
import com.malgn.application.devices.model.DeviceRegisterRequest;
import com.malgn.application.devices.model.DeviceResult;
import com.malgn.application.devices.provided.DeviceFinder;
import com.malgn.application.devices.provided.DeviceRegister;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/devices")
public class DeviceApiV1 {

    private final DeviceRegister deviceRegister;
    private final DeviceFinder deviceFinder;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public DeviceResponseV1 register(@RequestBody @Valid DeviceRegisterRequestV1 registerRequest) {

        DeviceResult result =
            deviceRegister.register(
                DeviceRegisterRequest.builder()
                    .teamId(registerRequest.teamId())
                    .deviceType(registerRequest.deviceType())
                    .name(registerRequest.name())
                    .description(registerRequest.description())
                    .model(registerRequest.model())
                    .manufacturer(registerRequest.manufacturer())
                    .serialNumber(registerRequest.serialNumber())
                    .os(registerRequest.os())
                    .osVersion(registerRequest.osVersion())
                    .cpu(registerRequest.cpu())
                    .memory(registerRequest.memory())
                    .storage(registerRequest.storage())
                    .options(registerRequest.options())
                    .ipAddress(registerRequest.ipAddress())
                    .purchaseDate(defaultIfNull(registerRequest.purchaseDate(), LocalDate.now()).atStartOfDay())
                    .build());

        return from(result);
    }

    @GetMapping(path = "")
    public Page<DeviceResponseV1> search(DeviceSearchRequestV1 searchRequest,
        @PageableDefault(sort = "createdDate", direction = Direction.DESC, size = 25) Pageable pageable) {

        Page<DeviceResult> result =
            deviceFinder.devices(
                DeviceFindRequest.builder()
                    .name(searchRequest.name())
                    .description(searchRequest.description())
                    .teamId(searchRequest.teamId())
                    .deviceType(searchRequest.deviceType())
                    .status(searchRequest.status())
                    .model(searchRequest.model())
                    .manufacturer(searchRequest.manufacturer())
                    .serialNumber(searchRequest.serialNumber())
                    .build(),
                pageable);

        return result.map(DeviceResponseV1::from);

    }

}
