package com.ohdocha.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminInfoRepository extends JpaRepository<AdminInfo, String> {

    Optional<AdminInfo> findTopByadminId(String adminId);
}
