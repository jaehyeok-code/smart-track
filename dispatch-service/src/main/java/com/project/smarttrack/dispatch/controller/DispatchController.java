package com.project.smarttrack.dispatch.controller;

import com.project.smarttrack.dispatch.domain.Dispatch;
import com.project.smarttrack.dispatch.service.DispatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispatches")
@RequiredArgsConstructor
public class DispatchController {

  private final DispatchService dispatchService;
  // 대기 주문 조회
  @GetMapping("/pending")
  public ResponseEntity<List<Dispatch>> getPendingDispatches() {
    return ResponseEntity.ok(dispatchService.getPending());
  }
  //기사 수락
  @PostMapping("/{id}/accept")
  public ResponseEntity<Void> acceptDispatch(
      @PathVariable("id") Long dispatchId,
      @RequestBody Long driverId) {
    dispatchService.accept(dispatchId, driverId);
    return ResponseEntity.ok().build();
  }
  // 기사 거절
  @PostMapping("/{id}/reject")
  public ResponseEntity<Void> rejectDispatch(
      @PathVariable("id") Long dispatchId,
      @RequestBody Long driverId) {
    dispatchService.reject(dispatchId, driverId);
    return ResponseEntity.ok().build();
  }
}
