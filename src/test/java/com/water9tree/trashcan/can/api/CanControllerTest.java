package com.water9tree.trashcan.can.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.water9tree.trashcan.ApiTestHelper;
import com.water9tree.trashcan.can.dto.request.CanSaveRequest;
import com.water9tree.trashcan.can.dto.request.CanSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CanControllerTest extends ApiTestHelper {

  private CanSaveRequest canSaveRequest;
  private CanSearchRequest canSearchRequest;

  @BeforeEach
  void setUp() {
    canSaveRequest = CanSaveRequest.builder()
        .floorNumber(1)
        .buildingId(417L)
        .name("제1사범관")
        .regular(80)
        .plastic(80)
        .paper(80)
        .bottle(80).build();

    canSearchRequest = CanSearchRequest.builder()
        .floorNumber(1)
        .buildingId(417L)
        .canId(1L)
        .build();
  }

  @Nested
  @DisplayName("쓰레기통 컨트롤러 테스트")
  class CanController {

    @Test
    public void 쓰레기통_생성을_성공한다() throws Exception {
      createCanUsingApi(canSaveRequest).andExpect(status().isCreated())
          .andDo(document("create-can",
              requestFields(
                  fieldWithPath("floorNumber").description("층 수"),
                  fieldWithPath("buildingId").description("빌딩 번호"),
                  fieldWithPath("name").description("빌딩 이름"),
                  fieldWithPath("regular").description("일반 쓰레기"),
                  fieldWithPath("bottle").description("캔/병 쓰레기"),
                  fieldWithPath("plastic").description("플라스틱 쓰레기"),
                  fieldWithPath("paper").description("종이 쓰레기")
              ),
              responseFields(
                  fieldWithPath("canId").description("쓰레기통 ID"),
                  fieldWithPath("regular").description("일반 쓰레기"),
                  fieldWithPath("bottle").description("캔/병 쓰레기"),
                  fieldWithPath("plastic").description("플라스틱 쓰레기"),
                  fieldWithPath("paper").description("종이 쓰레기"))
          )).andReturn();
    }

    @Test
    public void 쓰레기통_삭제를_성공한다() throws Exception {
      createCanUsingApi(canSaveRequest).andExpect(status().isCreated());

      em.flush();
      em.clear();

      deleteCanUsingApi(canSearchRequest).andExpect(status().isNoContent())
          .andDo(document("delete-can",
              requestFields(
                  fieldWithPath("floorNumber").description("층 수"),
                  fieldWithPath("buildingId").description("빌딩 번호"),
                  fieldWithPath("canId").description("쓰레기통 ID")
              )
          )).andReturn();
    }
  }
}
