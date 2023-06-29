package com.water9tree.trashcan.building.application;

import com.water9tree.trashcan.building.dto.request.BuildingSearchRequest;
import com.water9tree.trashcan.building.dto.request.BuildingSaveRequest;
import com.water9tree.trashcan.building.dto.response.BuildingSearchResponse;
import com.water9tree.trashcan.building.dto.response.BuildingSaveResponse;
import com.water9tree.trashcan.building.dao.BuildingRepository;
import com.water9tree.trashcan.building.entity.Building;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuildingService {

  private final BuildingRepository buildingRepository;
  private static final String BUILDING_NOT_FOUND = "찾을 수 없는 건물입니다.";

  @Transactional
  public BuildingSaveResponse save(BuildingSaveRequest request) {
    Building building = Building.builder()
        .buildingId(request.buildingId()) // TODO: 2023-06-29 buildingId 중복 여부 확인 필요
        .name(request.name())
        .build();

    return BuildingSaveResponse.from(buildingRepository.save(building));
  }

  @Transactional
  public void delete(BuildingSearchRequest request) {
    Building building = buildingRepository.findByBuildingId(request.buildingId())
        .orElseThrow(() -> new RuntimeException(BUILDING_NOT_FOUND));

    buildingRepository.delete(building);
  }

  @Transactional
  public void deleteAll() {
    buildingRepository.deleteAll();
  }

  public BuildingSearchResponse getAllFloorCans(BuildingSearchRequest request) {
    return BuildingSearchResponse.from(buildingRepository.findByBuildingId(request.buildingId())
        .orElseThrow(() -> new RuntimeException(BUILDING_NOT_FOUND)));
  }
}
