package com.malgn.adapter.web.api.v1.software.dto;

import com.malgn.domain.software.SoftwareStatus;

public record SoftwareSearchRequestV1(Long teamId,
                                      String name,
                                      SoftwareStatus status) {
}
