package com.water9tree.trashcan.can.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CanSearchRequest(
    @NotNull(message = "층 수를 입력해주세요.")
    Integer floorNumber,

    @NotNull(message = "빌딩 번호를 입력해주세요.")
    Long buildingId,

    @NotNull(message = "캔 ID를 입력해주세요.")
    Long canId) {

}
