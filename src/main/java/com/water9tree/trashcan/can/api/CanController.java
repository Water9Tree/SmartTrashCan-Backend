package com.water9tree.trashcan.can.api;

import com.water9tree.trashcan.can.application.CanService;
import com.water9tree.trashcan.can.dto.request.CanSaveRequest;
import com.water9tree.trashcan.can.dto.request.CanSearchRequest;
import com.water9tree.trashcan.can.dto.response.CanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/can")
public class CanController {

  private final CanService canService;

  @PostMapping("/create")
  public ResponseEntity<CanResponse> createCan(@RequestBody CanSaveRequest request) {
    CanResponse response = canService.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteCan(@RequestBody CanSearchRequest request) {
    canService.delete(request);
    return ResponseEntity.noContent().build();
  }
}
