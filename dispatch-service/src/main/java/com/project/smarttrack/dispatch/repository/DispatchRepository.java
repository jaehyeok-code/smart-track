package com.project.smarttrack.dispatch.repository;

import com.project.smarttrack.dispatch.domain.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends JpaRepository<Dispatch, Long> {

}
