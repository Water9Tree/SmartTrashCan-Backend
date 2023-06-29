package com.water9tree.trashcan.floor.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.water9tree.trashcan.ApiTestHelper;
import com.water9tree.trashcan.can.dto.request.CanSaveRequest;
import com.water9tree.trashcan.floor.dto.request.FloorSaveRequest;
import com.water9tree.trashcan.floor.dto.request.FloorSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FloorControllerTest extends ApiTestHelper {

  private FloorSaveRequest floorSaveRequest;
  private FloorSearchRequest floorSearchRequest;
  private CanSaveRequest canSaveRequest;

  @BeforeEach
  void setUp() {
    floorSaveRequest = FloorSaveRequest.builder()
        .buildingId(417L)
        .floorNumber(1)
        .name("제1사범관").build();

    floorSearchRequest = FloorSearchRequest.builder()
        .buildingId(417L)
        .floorNumber(1).build();

    canSaveRequest = CanSaveRequest.builder()
        .buildingId(417L)
        .name("제1사범관")
        .floorNumber(1)
        .regular(80)
        .plastic(80)
        .paper(80)
        .bottle(80).build();
  }

  @Nested
  class FloorController {

    @Test
    public void 층_생성_API_호출을_성공한다() throws Exception {
      createFloorUsingApi(floorSaveRequest).andExpect(status().isCreated())
          .andDo(document("create-floor",
              requestFields(
                  fieldWithPath("buildingId").description("빌딩 번호"),
                  fieldWithPath("name").description("빌딩 이름"),
                  fieldWithPath("floorNumber").description("층 수")
                  ),
              responseFields(
                  fieldWithPath("id").description("층 ID"),
                  fieldWithPath("floorNumber").description("층 수"))
          )).andReturn();
    }

    @Test
    public void 층_목록_조회를_성공한다() throws Exception {
      createCanUsingApi(canSaveRequest).andExpect(status().isCreated());

      em.flush();
      em.clear();

      getFloorUsingApi(floorSearchRequest).andExpect(status().isOk()).andDo(print())
          .andDo(document("get-floor",
              requestFields(
                  fieldWithPath("buildingId").description("빌딩 번호"),
                  fieldWithPath("floorNumber").description("층 수")
              ),
              responseFields(
                  fieldWithPath("floorNumber").description("층 수"),
                  fieldWithPath("trashCans[].canId").description("쓰레기통 index"),
                  fieldWithPath("trashCans[].regular").description("일반 쓰레기 양"),
                  fieldWithPath("trashCans[].bottle").description("캔/병 쓰레기 양"),
                  fieldWithPath("trashCans[].plastic").description("플라스틱 쓰레기 양"),
                  fieldWithPath("trashCans[].paper").description("종이 쓰레기 양"))
          )).andReturn();
    }

    @Test
    public void 층_등록_후_삭제_기능이_정상적으로_동작한다() throws Exception {
      createFloorUsingApi(floorSaveRequest).andExpect(status().isCreated());

      em.flush();
      em.clear();

      deleteFloorUsingApi(floorSearchRequest).andExpect(status().isNoContent())
          .andDo(document("delete-floor",
              requestFields(
                  fieldWithPath("buildingId").description("빌딩 번호"),
                  fieldWithPath("floorNumber").description("층 수")
              )
          )).andReturn();
    }
  }
}
