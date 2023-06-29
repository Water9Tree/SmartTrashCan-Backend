package com.water9tree.trashcan.floor.api;

import com.water9tree.trashcan.floor.application.FloorService;
import com.water9tree.trashcan.floor.dto.request.FloorSaveRequest;
import com.water9tree.trashcan.floor.dto.request.FloorSearchRequest;
import com.water9tree.trashcan.floor.dto.response.FloorSearchResponse;
import com.water9tree.trashcan.floor.dto.response.FloorSaveResponse;
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
@RequestMapping("/floor")
public class FloorController {

  private final FloorService floorService;

  @PostMapping("/create")
  public ResponseEntity<FloorSaveResponse> createFloor(@RequestBody FloorSaveRequest request) {
    FloorSaveResponse response = floorService.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping
  public ResponseEntity<FloorSearchResponse> getFloor(@RequestBody FloorSearchRequest request) {
    FloorSearchResponse response = floorService.getFloor(request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteBuilding(@RequestBody FloorSearchRequest request) {
    floorService.delete(request);
    return ResponseEntity.noContent().build();
  }
}
