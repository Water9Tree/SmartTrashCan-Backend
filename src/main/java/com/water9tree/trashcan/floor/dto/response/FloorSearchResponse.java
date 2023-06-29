package com.water9tree.trashcan.floor.dto.response;

import com.water9tree.trashcan.can.dto.response.CanResponse;
import com.water9tree.trashcan.floor.entity.Floor;
import java.util.List;
import lombok.Builder;

@Builder
public record FloorSearchResponse(Integer floorNumber,
                                  List<CanResponse> trashCans) {

  public static FloorSearchResponse from(Floor floor) {
    return FloorSearchResponse.builder()
        .floorNumber(floor.getFloorNumber())
        .trashCans(floor.getCans().stream()
            .map(CanResponse::from)
            .toList()).build();
  }
}
