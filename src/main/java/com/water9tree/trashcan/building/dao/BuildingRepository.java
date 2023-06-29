package com.water9tree.trashcan.building.dao;

import com.water9tree.trashcan.building.entity.Building;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {

  Optional<Building> findByBuildingId(Long buildingId);
}
