package com.water9tree.trashcan.can.application;

import com.water9tree.trashcan.building.dao.BuildingRepository;
import com.water9tree.trashcan.building.entity.Building;
import com.water9tree.trashcan.can.dto.request.CanSaveRequest;
import com.water9tree.trashcan.can.dto.request.CanSearchRequest;
import com.water9tree.trashcan.can.dto.response.CanResponse;
import com.water9tree.trashcan.can.dao.CanRepository;
import com.water9tree.trashcan.can.entity.Can;
import com.water9tree.trashcan.floor.dao.FloorRepository;
import com.water9tree.trashcan.floor.entity.Floor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CanService {

  private final BuildingRepository buildingRepository;
  private final FloorRepository floorRepository;
  private final CanRepository canRepository;

  private static final String BUILDING_NOT_FOUND = "찾을 수 없는 건물입니다.";
  private static final String FLOOR_NOT_FOUND = "찾을 수 없는 건물의 층입니다.";
  private static final String TRASH_CAN_NOT_FOUND = "찾을 수 없는 쓰레기통입니다.";

  @Transactional
  public CanResponse save(CanSaveRequest request) {
    Building building = Building.builder()
        .buildingId(request.buildingId())
        .name(request.name())
        .build();

    Floor floor = Floor.builder()
        .floorNumber(request.floorNumber())
        .building(building)
        .build();

    Can can = Can.builder()
        .regular(request.regular())
        .bottle(request.bottle())
        .plastic(request.plastic())
        .paper(request.paper())
        .floor(floor)
        .build();

    return CanResponse.from(canRepository.save(can));
  }

  @Transactional
  public void delete(CanSearchRequest request) {
    canRepository.delete(findByCanDAO(request));
  }

  @Transactional
  public void deleteAll() {
    canRepository.deleteAll();
  }

  public Can findByCanDAO(CanSearchRequest request) {
    Building building = buildingRepository.findByBuildingId(request.buildingId())
        .orElseThrow(() -> new RuntimeException(BUILDING_NOT_FOUND));

    Floor floor = floorRepository.findByFloorNumberAndBuilding(request.floorNumber(), building)
        .orElseThrow(() -> new RuntimeException(FLOOR_NOT_FOUND));

    return canRepository.findByFloorAndId(floor, request.canId())
        .orElseThrow(() -> new RuntimeException(TRASH_CAN_NOT_FOUND));
  }
}
