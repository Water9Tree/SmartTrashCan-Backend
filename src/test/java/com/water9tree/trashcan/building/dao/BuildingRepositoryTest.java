package com.water9tree.trashcan.building.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.water9tree.trashcan.IntegrationTest;
import com.water9tree.trashcan.building.entity.Building;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BuildingRepositoryTest extends IntegrationTest {

  @Autowired
  BuildingRepository buildingRepository;

  @Nested
  @DisplayName("빌딩 레파지토리 테스트")
  class BuildingTest {

    @Test
    public void 빌딩의_상태를_저장한다() throws Exception {
      buildingRepository.save(Building.builder()
          .buildingId(417L)
          .name("제1사범관")
          .build());

      List<Building> trashCanList = buildingRepository.findAll();
      assertThat(trashCanList.size()).isEqualTo(1);
    }
  }
}
