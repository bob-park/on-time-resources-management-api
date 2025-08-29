package com.malgn.application.software.provided;

import com.malgn.application.software.model.SoftwareRegisterRequest;
import com.malgn.application.software.model.SoftwareResult;

public interface SoftwareRegister {

    SoftwareResult register(SoftwareRegisterRequest registerRequest);

}
