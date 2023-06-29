package com.water9tree.trashcan.can.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.water9tree.trashcan.IntegrationTest;
import com.water9tree.trashcan.can.entity.Can;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CanRepositoryTest extends IntegrationTest {

  @Autowired
  CanRepository canRepository;

  @Nested
  @DisplayName("쓰레기통 레파지토리 테스트")
  class CanTest {

    @Test
    public void 쓰레기통의_상태를_저장한다() throws Exception {
      canRepository.save(Can.builder()
          .regular(80)
          .bottle(80)
          .plastic(80)
          .paper(80).build());

      List<Can> trashCanList = canRepository.findAll();
      assertThat(trashCanList.size()).isEqualTo(1);
    }
  }
}
