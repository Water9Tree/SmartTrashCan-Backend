package com.water9tree.trashcan;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

import com.water9tree.trashcan.building.dto.request.BuildingSaveRequest;
import com.water9tree.trashcan.building.dto.request.BuildingSearchRequest;
import com.water9tree.trashcan.can.dto.request.CanSaveRequest;
import com.water9tree.trashcan.can.dto.request.CanSearchRequest;
import com.water9tree.trashcan.floor.dto.request.FloorSaveRequest;
import com.water9tree.trashcan.floor.dto.request.FloorSearchRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class ApiTestHelper extends IntegrationTest {
  protected ResultActions createBuildingUsingApi(BuildingSaveRequest request) throws Exception {
    return mockMvc.perform(post("/building/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }

  protected ResultActions getBuildingUsingApi(BuildingSearchRequest request) throws Exception {
    return mockMvc.perform(post("/building")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }

  protected ResultActions deleteBuildingUsingApi(BuildingSearchRequest request) throws Exception {
    return mockMvc.perform(delete("/building")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }

  protected ResultActions createCanUsingApi(CanSaveRequest request) throws Exception {
    return mockMvc.perform(post("/can/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }

  protected ResultActions getCanUsingApi(CanSearchRequest request) throws Exception {
    return mockMvc.perform(post("/can")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }

  protected ResultActions deleteCanUsingApi(CanSearchRequest request) throws Exception {
    return mockMvc.perform(delete("/can")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }

  protected ResultActions createFloorUsingApi(FloorSaveRequest request) throws Exception {
    return mockMvc.perform(post("/floor/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }

  protected ResultActions getFloorUsingApi(FloorSearchRequest request) throws Exception {
    return mockMvc.perform(post("/floor")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }

  protected ResultActions deleteFloorUsingApi(FloorSearchRequest request) throws Exception {
    return mockMvc.perform(delete("/floor")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }
}
