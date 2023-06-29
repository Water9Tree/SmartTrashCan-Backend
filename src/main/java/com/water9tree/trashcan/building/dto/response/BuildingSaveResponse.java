package com.water9tree.trashcan.building.dto.response;

import com.water9tree.trashcan.building.entity.Building;
import lombok.Builder;

@Builder
public record BuildingSaveResponse(Long id,
                                   Long buildingId,
                                   String name) {

  public static BuildingSaveResponse from(Building building) {
    return BuildingSaveResponse.builder()
        .id(building.getId())
        .buildingId(building.getBuildingId())
        .name(building.getName())
        .build();
  }
}
