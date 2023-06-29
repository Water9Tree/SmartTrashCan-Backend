package com.water9tree.trashcan.can.dto.response;

import com.water9tree.trashcan.can.entity.Can;
import lombok.Builder;

@Builder
public record CanResponse(Long canId,
                          Integer regular,
                          Integer bottle,
                          Integer plastic,
                          Integer paper) {

  public static CanResponse from(Can can) {
    return CanResponse.builder()
        .canId(can.getId())
        .regular(can.getRegular())
        .bottle(can.getBottle())
        .plastic(can.getPlastic())
        .paper(can.getPaper())
        .build();
  }
}
