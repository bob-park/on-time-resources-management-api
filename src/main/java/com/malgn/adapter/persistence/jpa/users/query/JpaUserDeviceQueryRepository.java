package com.malgn.adapter.persistence.jpa.users.query;

import java.util.List;

import com.malgn.domain.users.UserDevice;

public interface JpaUserDeviceQueryRepository {

    List<UserDevice> userDevices(Long userId);

}
