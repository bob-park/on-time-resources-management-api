package com.malgn.adapter.persistence.jpa.users;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import com.malgn.application.users.required.UserDeviceRepository;
import com.malgn.domain.users.UserDevice;

@RequiredArgsConstructor
@Repository
public class JpaUserDeviceRepositoryAdapter implements UserDeviceRepository {

    private final JpaUserDeviceRepository userDeviceRepository;

    @Override
    public List<UserDevice> userDevices(Long userId) {
        return userDeviceRepository.userDevices(userId);
    }
}
