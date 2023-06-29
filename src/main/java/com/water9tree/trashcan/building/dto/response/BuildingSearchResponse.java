package com.water9tree.trashcan.building.dto.response;

import com.water9tree.trashcan.building.entity.Building;
import com.water9tree.trashcan.floor.dto.response.FloorSearchResponse;
import java.util.List;
import lombok.Builder;

@Builder
public record BuildingSearchResponse(Long buildingId,
                                     String name,
                                     List<FloorSearchResponse> floors) {

  public static BuildingSearchResponse from(Building building) {
    return BuildingSearchResponse.builder()
        .buildingId(building.getBuildingId())
        .name(building.getName())
        .floors(building.getFloors().stream()
            .map(FloorSearchResponse::from)
            .toList()).build();
  }
}
