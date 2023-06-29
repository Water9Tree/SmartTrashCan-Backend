package com.water9tree.trashcan.building.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.water9tree.trashcan.floor.entity.Floor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "building")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Building {

  private static final int MAX_NAME_LENGTH = 20;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "building_id", nullable = false)
  private Long buildingId;

  @Column(name = "name", length = MAX_NAME_LENGTH)
  private String name;

  @OneToMany(mappedBy = "building", cascade = CascadeType.REMOVE)
  private final List<Floor> floors = new ArrayList<>();

  @Builder
  public Building(Long buildingId, String name) {
    this.buildingId = buildingId;
    this.name = name;
  }
}
