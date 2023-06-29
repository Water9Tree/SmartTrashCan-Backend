package com.water9tree.trashcan.floor.application;

import com.water9tree.trashcan.building.dao.BuildingRepository;
import com.water9tree.trashcan.building.entity.Building;
import com.water9tree.trashcan.floor.dto.request.FloorSaveRequest;
import com.water9tree.trashcan.floor.dto.request.FloorSearchRequest;
import com.water9tree.trashcan.floor.dto.response.FloorSearchResponse;
import com.water9tree.trashcan.floor.dto.response.FloorSaveResponse;
import com.water9tree.trashcan.floor.dao.FloorRepository;
import com.water9tree.trashcan.floor.entity.Floor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FloorService {

  private final BuildingRepository buildingRepository;
  private final FloorRepository floorRepository;

  private static final String FLOOR_NOT_FOUND = "찾을 수 없는 건물의 층입니다.";
  private static final String BUILDING_NOT_FOUND = "찾을 수 없는 건물입니다.";

  @Transactional
  public FloorSaveResponse save(FloorSaveRequest request) {
    Building building = Building.builder()
        .buildingId(request.buildingId())
        .name(request.name())
        .build();

    Floor floor = Floor.builder()
        .floorNumber(request.floorNumber())
        .building(building)
        .build();

    return FloorSaveResponse.from(floorRepository.save(floor));
  }

  @Transactional
  public void delete(FloorSearchRequest request) {
    floorRepository.delete(findByFloorDAO(request));
  }

  @Transactional
  public void deleteAll() {
    floorRepository.deleteAll();
  }

  public FloorSearchResponse getFloor(FloorSearchRequest request) {
    return FloorSearchResponse.from(findByFloorDAO(request));
  }

  public Floor findByFloorDAO(FloorSearchRequest request) {
    Building building = buildingRepository.findByBuildingId(request.buildingId())
        .orElseThrow(() -> new RuntimeException(BUILDING_NOT_FOUND));

    return floorRepository.findByFloorNumberAndBuilding(request.floorNumber(), building)
        .orElseThrow(() -> new RuntimeException(FLOOR_NOT_FOUND));
  }

}
