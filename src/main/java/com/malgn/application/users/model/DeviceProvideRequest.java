package com.malgn.application.users.model;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record DeviceProvideRequest(LocalDateTime startDate,
                                   LocalDateTime endDate) {
}
