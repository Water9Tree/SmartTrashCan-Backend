package com.water9tree.trashcan.floor.dao;

import com.water9tree.trashcan.building.entity.Building;
import com.water9tree.trashcan.floor.entity.Floor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorRepository extends JpaRepository<Floor, Long> {

  Optional<Floor> findByFloorNumberAndBuilding(Integer floorNumber, Building building);
}
