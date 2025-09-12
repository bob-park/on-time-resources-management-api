package com.malgn.adapter.persistence.jpa.software.query.impl;

import static com.malgn.domain.software.QSoftware.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import org.apache.commons.lang.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.malgn.adapter.persistence.jpa.software.query.JpaSoftwareQueryRepository;
import com.malgn.application.software.model.SoftwareFindRequest;
import com.malgn.common.querydsl.model.QueryDslPath;
import com.malgn.common.querydsl.utils.QueryRepositoryUtils;
import com.malgn.domain.software.Software;
import com.malgn.domain.software.SoftwareStatus;

@RequiredArgsConstructor
public class JpaSoftwareQueryRepositoryImpl implements JpaSoftwareQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<Software> findSoftware(SoftwareFindRequest findRequest, Pageable pageable) {

        List<Software> content =
            query.selectFrom(software)
                .where(mappingCondition(findRequest))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(sort(pageable))
                .fetch();

        JPAQuery<Long> countQuery =
            query.select(software.id.count()).from(software)
                .where(mappingCondition(findRequest));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private Predicate mappingCondition(SoftwareFindRequest findRequest) {

        BooleanBuilder builder = new BooleanBuilder();

        builder
            .and(eqTeamId(findRequest.teamId()))
            .and(containName(findRequest.name()))
            .and(eqStatus(findRequest.status()));

        return builder;
    }

    private BooleanExpression eqTeamId(Long teamId) {
        return teamId != null ? software.teamId.eq(teamId) : null;
    }

    private BooleanExpression containName(String name) {
        return StringUtils.isNotBlank(name) ? software.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression eqStatus(SoftwareStatus status) {
        return status != null ? software.status.eq(status) : null;
    }

    private OrderSpecifier<?>[] sort(Pageable pageable) {
        return QueryRepositoryUtils.sort(
            pageable,
            List.of(
                new QueryDslPath<>("id", software.id),
                new QueryDslPath<>("name", software.name),
                new QueryDslPath<>("platform", software.platform),
                new QueryDslPath<>("status", software.status),
                new QueryDslPath<>("purchaseDate", software.purchaseDate),
                new QueryDslPath<>("createdDate", software.createdDate)));
    }
}
