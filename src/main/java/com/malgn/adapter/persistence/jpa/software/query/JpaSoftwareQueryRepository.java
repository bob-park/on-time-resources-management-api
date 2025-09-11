package com.malgn.adapter.persistence.jpa.software.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.malgn.application.software.model.SoftwareFindRequest;
import com.malgn.domain.software.Software;

public interface JpaSoftwareQueryRepository {

    Page<Software> findSoftware(SoftwareFindRequest findRequest, Pageable pageable);

}
