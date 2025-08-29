package com.malgn.application.software.service;

import static com.malgn.application.software.model.SoftwareResult.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.application.software.model.SoftwareRegisterRequest;
import com.malgn.application.software.model.SoftwareResult;
import com.malgn.application.software.provided.SoftwareRegister;
import com.malgn.application.software.required.SoftwareRepository;
import com.malgn.domain.software.Software;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class SoftwareCommandService implements SoftwareRegister {

    private final SoftwareRepository softwareRepository;

    @Override
    public SoftwareResult register(SoftwareRegisterRequest registerRequest) {

        Software createdSoftware =
            Software.builder()
                .teamId(registerRequest.teamId())
                .platform(registerRequest.platform())
                .name(registerRequest.name())
                .description(registerRequest.description())
                .manufacturer(registerRequest.manufacturer())
                .version(registerRequest.version())
                .options(registerRequest.options())
                .purchaseDate(registerRequest.purchaseDate())
                .licenseKey(registerRequest.licenseKey())
                .licenseStartDate(registerRequest.licenseStartDate())
                .licenseEndDate(registerRequest.licenseEndDate())
                .build();

        createdSoftware = softwareRepository.save(createdSoftware);

        log.debug("registered software. ({})", createdSoftware);

        return from(createdSoftware);
    }
}
