package com.malgn.adapter.web.api.v1.users.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DeviceProvideRequestV1(@NotNull LocalDate startDate,
                                     LocalDate endDate) {
}
