package com.project.smarttrack.dispatch.repository;

import com.project.smarttrack.common.domain.DispatchStatus;
import com.project.smarttrack.dispatch.domain.Dispatch;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends JpaRepository<Dispatch, Long> {


  // ASSIGNED 상태(배차 요청 대기)인 모든 Dispatch
  List<Dispatch> findAllByStatus(DispatchStatus status);
}
