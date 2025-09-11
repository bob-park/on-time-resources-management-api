package com.malgn.adapter.persistence.jpa.software;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.malgn.application.software.model.SoftwareFindRequest;
import com.malgn.application.software.required.SoftwareRepository;
import com.malgn.domain.software.Software;

@RequiredArgsConstructor
@Repository
public class JpaSoftwareRepositoryAdapter implements SoftwareRepository {

    private final JpaSoftwareRepository softwareRepository;

    @Override
    public Software save(Software software) {
        return softwareRepository.save(software);
    }

    @Override
    public Page<Software> findSoftware(SoftwareFindRequest findRequest, Pageable pageable) {
        return softwareRepository.findSoftware(findRequest, pageable);
    }
}
