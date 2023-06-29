package com.water9tree.trashcan.building.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.water9tree.trashcan.ApiTestHelper;
import com.water9tree.trashcan.building.dto.request.BuildingSearchRequest;
import com.water9tree.trashcan.building.dto.request.BuildingSaveRequest;
import com.water9tree.trashcan.can.dto.request.CanSaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BuildingControllerTest extends ApiTestHelper {

  private BuildingSaveRequest buildingSaveRequest;
  private BuildingSearchRequest buildingSearchRequest;
  private CanSaveRequest canSaveRequest;

  @BeforeEach
  void setUp() {
    buildingSaveRequest = BuildingSaveRequest.builder()
        .buildingId(417L)
        .name("제1사범관").build();

    buildingSearchRequest = BuildingSearchRequest.builder()
        .buildingId(417L).build();

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
  class BuildingController {

    @Test
    public void 빌딩_생성을_성공한다() throws Exception {
      createBuildingUsingApi(buildingSaveRequest).andExpect(status().isCreated())
          .andDo(document("create-building",
              requestFields(
                  fieldWithPath("buildingId").description("빌딩 번호"),
                  fieldWithPath("name").description("빌딩 이름")
              ),
              responseFields(
                  fieldWithPath("id").description("빌딩 index"),
                  fieldWithPath("buildingId").description("빌딩 번호"),
                  fieldWithPath("name").description("빌딩 이름"))
          )).andReturn();
    }

    @Test
    public void 쓰레기통_등록_후_데이터를_가져온다() throws Exception {
      createCanUsingApi(canSaveRequest).andExpect(status().isCreated());

      em.flush();
      em.clear();

      getBuildingUsingApi(buildingSearchRequest).andExpect(status().isOk()).andDo(print())
          .andDo(document("get-building",
              requestFields(
                  fieldWithPath("buildingId").description("빌딩 번호")
              ),
              responseFields(
                  fieldWithPath("buildingId").description("빌딩 번호"),
                  fieldWithPath("name").description("빌딩 이름"),
                  fieldWithPath("floors[].floorNumber").description("층 수"),
                  fieldWithPath("floors[].trashCans[].canId").description("쓰레기통 index"),
                  fieldWithPath("floors[].trashCans[].regular").description("일반 쓰레기 양"),
                  fieldWithPath("floors[].trashCans[].bottle").description("캔/병 쓰레기 양"),
                  fieldWithPath("floors[].trashCans[].plastic").description("플라스틱 쓰레기 양"),
                  fieldWithPath("floors[].trashCans[].paper").description("종이 쓰레기 양"))
          )).andReturn();
    }

    @Test
    public void 쓰레기통_등록_후_삭제_기능이_정상적으로_동작한다() throws Exception {
      createCanUsingApi(canSaveRequest).andExpect(status().isCreated());

      em.flush();
      em.clear();

      deleteBuildingUsingApi(buildingSearchRequest).andExpect(status().isNoContent())
          .andDo(document("delete-building",
              requestFields(
                  fieldWithPath("buildingId").description("빌딩 번호")
              )
          )).andReturn();
    }
  }
}
