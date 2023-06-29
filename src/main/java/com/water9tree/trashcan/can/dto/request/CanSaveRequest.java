package com.water9tree.trashcan.can.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CanSaveRequest(
    @NotNull(message = "층 수를 입력해주세요.")
    Integer floorNumber,

    @NotNull(message = "빌딩 번호를 입력해주세요.")
    Long buildingId,

    @NotNull(message = "빌딩 이름을 입력해주세요.")
    String name,

    @NotNull(message = "일반 쓰레기 양을 입력해주세요.")
    Integer regular,

    @NotNull(message = "캔/병 쓰레기 양을 입력해주세요.")
    Integer bottle,

    @NotNull(message = "플라스틱 쓰레기 양을 입력해주세요.")
    Integer plastic,

    @NotNull(message = "종이 쓰레기 양을 입력해주세요.")
    Integer paper) {

}
