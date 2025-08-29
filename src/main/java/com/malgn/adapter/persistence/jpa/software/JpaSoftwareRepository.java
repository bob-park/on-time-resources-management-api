package com.malgn.adapter.persistence.jpa.software;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malgn.domain.software.Software;

public interface JpaSoftwareRepository extends JpaRepository<Software, Long> {
}
