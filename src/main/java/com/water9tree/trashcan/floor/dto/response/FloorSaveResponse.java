package com.water9tree.trashcan.floor.dto.response;

import com.water9tree.trashcan.floor.entity.Floor;
import lombok.Builder;

@Builder
public record FloorSaveResponse(Long id,
                                Integer floorNumber) {

  public static FloorSaveResponse from(Floor floor) {
    return FloorSaveResponse.builder()
        .id(floor.getId())
        .floorNumber(floor.getFloorNumber())
        .build();
  }
}
