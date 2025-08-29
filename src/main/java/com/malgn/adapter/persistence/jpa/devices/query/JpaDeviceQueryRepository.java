package com.malgn.adapter.persistence.jpa.devices.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.malgn.application.devices.model.DeviceFindRequest;
import com.malgn.domain.devices.Device;

public interface JpaDeviceQueryRepository {

    Page<Device> findDevices(DeviceFindRequest findRequest, Pageable pageable);

}
