package com.malgn.application.software.model;

import lombok.Builder;

import com.malgn.domain.software.SoftwareStatus;

@Builder
public record SoftwareFindRequest(Long teamId,
                                  String name,
                                  SoftwareStatus status) {
}
