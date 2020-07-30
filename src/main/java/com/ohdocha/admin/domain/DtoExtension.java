package com.ohdocha.admin.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Slf4j
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class DtoExtension {

}