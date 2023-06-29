package com.water9tree.trashcan.building.api;

import com.water9tree.trashcan.building.application.BuildingService;
import com.water9tree.trashcan.building.dto.request.BuildingSearchRequest;
import com.water9tree.trashcan.building.dto.request.BuildingSaveRequest;
import com.water9tree.trashcan.building.dto.response.BuildingSearchResponse;
import com.water9tree.trashcan.building.dto.response.BuildingSaveResponse;
import java.util.List;
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
@RequestMapping("/building")
public class BuildingController {

  private final BuildingService buildingService;

  @PostMapping("/create")
  public ResponseEntity<BuildingSaveResponse> createBuilding(@RequestBody BuildingSaveRequest request) {
    BuildingSaveResponse response = buildingService.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping
  public ResponseEntity<BuildingSearchResponse> getAllFloorCans(@RequestBody BuildingSearchRequest request) {
    BuildingSearchResponse response = buildingService.getAllFloorCans(request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteBuilding(@RequestBody BuildingSearchRequest request) {
    buildingService.delete(request);
    return ResponseEntity.noContent().build();
  }

}
