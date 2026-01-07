package com.ohgiraffers.cqrs.cofig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditingf 활성화
// 엔티티의 생성/수정 시각을 자동의로 기록하는 기능
public class JpaConfig {
}
