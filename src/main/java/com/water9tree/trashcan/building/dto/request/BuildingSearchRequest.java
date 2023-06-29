package com.water9tree.trashcan.building.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BuildingSearchRequest(
    @NotNull(message = "빌딩 번호를 입력해주세요.")
    Long buildingId) {

}
