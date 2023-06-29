package com.water9tree.trashcan.floor.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FloorSaveRequest(
    @NotNull(message = "층 수를 입력해주세요.")
    Integer floorNumber,

    @NotNull(message = "빌딩 번호를 입력해주세요.")
    Long buildingId,

    @NotNull(message = "빌딩 이름을 입력해주세요.")
    String name) {

}
