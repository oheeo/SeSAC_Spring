package org.zerock.api01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.api01.domain.APIUser;

// APIUser는 일반 웹 서비스와 달리 Access Key를 발급받을 때 자신의 mid와 mpw를 이용
public interface APIUserRepository extends JpaRepository<APIUser, String> {
}
