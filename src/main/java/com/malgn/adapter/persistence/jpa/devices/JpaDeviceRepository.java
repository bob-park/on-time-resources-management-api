package com.malgn.adapter.persistence.jpa.devices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.domain.devices.Device;

public interface JpaDeviceRepository extends JpaRepository<Device, Long> {
}
