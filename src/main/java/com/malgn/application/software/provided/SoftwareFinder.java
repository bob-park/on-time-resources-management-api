package com.malgn.application.software.provided;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.malgn.application.software.model.SoftwareFindRequest;
import com.malgn.application.software.model.SoftwareResult;

public interface SoftwareFinder {

    Page<SoftwareResult> findSoftware(SoftwareFindRequest findRequest, Pageable pageable);

}
