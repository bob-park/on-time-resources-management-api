package com.malgn.adapter.persistence.jpa.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.adapter.persistence.jpa.users.query.JpaUserDeviceQueryRepository;
import com.malgn.domain.users.UserDevice;

public interface JpaUserDeviceRepository extends JpaRepository<UserDevice, Long>, JpaUserDeviceQueryRepository {
}
