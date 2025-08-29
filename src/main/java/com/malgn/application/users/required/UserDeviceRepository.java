package com.malgn.application.users.required;

import java.util.List;

import com.malgn.domain.users.UserDevice;

public interface UserDeviceRepository {

    List<UserDevice> userDevices(Long userId);

}
