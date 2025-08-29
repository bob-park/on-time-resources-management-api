package com.malgn.adapter.persistence.jpa.users.query.impl;

import static com.malgn.domain.devices.QDevice.*;
import static com.malgn.domain.users.QUserDevice.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.adapter.persistence.jpa.users.query.JpaUserDeviceQueryRepository;
import com.malgn.domain.users.UserDevice;

@RequiredArgsConstructor
public class JpaUserDeviceQueryRepositoryImpl implements JpaUserDeviceQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<UserDevice> userDevices(Long userId) {
        return query.selectFrom(userDevice)
            .join(userDevice.device, device).fetchJoin()
            .where(userDevice.userId.eq(userId))
            .orderBy(userDevice.id.asc())
            .fetch();
    }
}
