package com.malgn.adapter.persistence.jpa.devices.query.impl;

import static com.malgn.domain.devices.QDevice.*;
import static com.malgn.domain.users.QUserDevice.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import org.apache.commons.lang3.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.adapter.persistence.jpa.devices.query.JpaDeviceQueryRepository;
import com.malgn.application.devices.model.DeviceFindRequest;
import com.malgn.common.querydsl.model.QueryDslPath;
import com.malgn.common.querydsl.utils.QueryRepositoryUtils;
import com.malgn.domain.devices.Device;
import com.malgn.domain.devices.DeviceStatus;
import com.malgn.domain.devices.DeviceType;

@RequiredArgsConstructor
public class JpaDeviceQueryRepositoryImpl implements JpaDeviceQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<Device> findDevices(DeviceFindRequest findRequest, Pageable pageable) {

        List<Device> content =
            query.selectFrom(device)
                .leftJoin(device.userDevice, userDevice).fetchJoin()
                .where(mappingCondition(findRequest))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(sort(pageable))
                .orderBy(device.id.desc())
                .fetch();

        JPAQuery<Long> countQuery =
            query.select(device.id.count())
                .from(device)
                .where(mappingCondition(findRequest));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private Predicate mappingCondition(DeviceFindRequest findRequest) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(containName(findRequest.name()))
            .and(containDescription(findRequest.description()))
            .and(eqTeamId(findRequest.teamId()))
            .and(eqDeviceType(findRequest.deviceType()))
            .and(eqDeviceStatus(findRequest.status()))
            .and(containModel(findRequest.model()))
            .and(containManufacturer(findRequest.manufacturer()))
            .and(containSerialNumber(findRequest.serialNumber()))
            .and(eqUserId(findRequest.userId()));

        return builder;
    }

    private BooleanExpression containName(String name) {
        return StringUtils.isNotBlank(name) ? device.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression containDescription(String description) {
        return StringUtils.isNotBlank(description) ? device.description.containsIgnoreCase(description) : null;
    }

    private BooleanExpression eqTeamId(Long teamId) {
        return teamId != null ? device.teamId.eq(teamId) : null;
    }

    private BooleanExpression eqDeviceType(DeviceType deviceType) {
        return deviceType != null ? device.deviceType.eq(deviceType) : null;
    }

    private BooleanExpression eqDeviceStatus(DeviceStatus status) {
        return status != null ? device.status.eq(status) : null;
    }

    private BooleanExpression containModel(String model) {
        return StringUtils.isNotBlank(model) ? device.model.containsIgnoreCase(model) : null;
    }

    private BooleanExpression containManufacturer(String manufacturer) {
        return StringUtils.isNotBlank(manufacturer) ? device.manufacturer.containsIgnoreCase(manufacturer) : null;
    }

    private BooleanExpression containSerialNumber(String serialNumber) {
        return StringUtils.isNotBlank(serialNumber) ? device.serialNumber.containsIgnoreCase(serialNumber) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? userDevice.userId.eq(userId) : null;
    }

    private OrderSpecifier<?>[] sort(Pageable pageable) {
        return QueryRepositoryUtils.sort(
            pageable,
            List.of(
                new QueryDslPath<>("id", device.id),
                new QueryDslPath<>("deviceType", device.deviceType),
                new QueryDslPath<>("name", device.name),
                new QueryDslPath<>("status", device.status),
                new QueryDslPath<>("purchaseDate", device.purchaseDate),
                new QueryDslPath<>("createdDate", device.createdDate)));
    }

}
