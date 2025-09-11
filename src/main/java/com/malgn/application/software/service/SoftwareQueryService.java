package com.malgn.application.software.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.application.software.model.SoftwareFindRequest;
import com.malgn.application.software.model.SoftwareResult;
import com.malgn.application.software.provided.SoftwareFinder;
import com.malgn.application.software.required.SoftwareRepository;
import com.malgn.domain.software.Software;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SoftwareQueryService implements SoftwareFinder {

    private final SoftwareRepository softwareRepository;

    @Override
    public Page<SoftwareResult> findSoftware(SoftwareFindRequest findRequest, Pageable pageable) {

        Page<Software> result = softwareRepository.findSoftware(findRequest, pageable);

        return result.map(SoftwareResult::from);
    }
}
