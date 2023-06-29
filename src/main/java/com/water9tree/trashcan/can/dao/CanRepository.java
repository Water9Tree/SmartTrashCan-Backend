package com.water9tree.trashcan.can.dao;

import com.water9tree.trashcan.can.entity.Can;
import com.water9tree.trashcan.floor.entity.Floor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanRepository extends JpaRepository<Can, Long> {

  Optional<Can> findByFloorAndId(Floor floor, Long id);
}
