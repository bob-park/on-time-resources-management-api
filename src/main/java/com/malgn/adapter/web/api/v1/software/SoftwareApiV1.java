package com.malgn.adapter.web.api.v1.software;

import static com.malgn.adapter.web.api.v1.software.dto.SoftwareResponseV1.*;
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

import com.malgn.adapter.web.api.v1.software.dto.SoftwareRegisterRequestV1;
import com.malgn.adapter.web.api.v1.software.dto.SoftwareResponseV1;
import com.malgn.adapter.web.api.v1.software.dto.SoftwareSearchRequestV1;
import com.malgn.application.software.model.SoftwareFindRequest;
import com.malgn.application.software.model.SoftwareRegisterRequest;
import com.malgn.application.software.model.SoftwareResult;
import com.malgn.application.software.provided.SoftwareFinder;
import com.malgn.application.software.provided.SoftwareRegister;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/software")
public class SoftwareApiV1 {

    private final SoftwareRegister softwareRegister;
    private final SoftwareFinder softwareFinder;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public SoftwareResponseV1 register(@RequestBody @Valid SoftwareRegisterRequestV1 registerRequest) {

        SoftwareResult result =
            softwareRegister.register(
                SoftwareRegisterRequest.builder()
                    .teamId(registerRequest.teamId())
                    .platform(registerRequest.platform())
                    .name(registerRequest.name())
                    .description(registerRequest.description())
                    .manufacturer(registerRequest.manufacturer())
                    .version(registerRequest.version())
                    .options(registerRequest.options())
                    .purchaseDate(defaultIfNull(registerRequest.purchaseDate(), LocalDate.now()).atStartOfDay())
                    .licenseKey(registerRequest.licenseKey())
                    .licenseStartDate(
                        registerRequest.licenseStartDate() != null ?
                            registerRequest.licenseStartDate().atStartOfDay() : null)
                    .licenseEndDate(
                        registerRequest.licenseEndDate() != null ?
                            registerRequest.licenseEndDate().atStartOfDay() : null)
                    .build());

        return from(result);
    }

    @GetMapping(path = "")
    public Page<SoftwareResponseV1> search(SoftwareSearchRequestV1 searchRequest,
        @PageableDefault(size = 25, sort = "createdDate", direction = Direction.DESC) Pageable pageable) {

        Page<SoftwareResult> result =
            softwareFinder.findSoftware(
                SoftwareFindRequest.builder()
                    .teamId(searchRequest.teamId())
                    .name(searchRequest.name())
                    .status(searchRequest.status())
                    .build(),
                pageable);

        return result.map(SoftwareResponseV1::from);
    }

}
