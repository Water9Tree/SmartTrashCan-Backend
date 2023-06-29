package com.water9tree.trashcan.floor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.water9tree.trashcan.building.entity.Building;
import com.water9tree.trashcan.can.entity.Can;
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
@Table(name = "floor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Floor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "floor_number", nullable = false)
  private Integer floorNumber;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "building_id")
  private Building building;

  @OneToMany(mappedBy = "floor", cascade = CascadeType.REMOVE)
  private final List<Can> cans = new ArrayList<>();

  @Builder
  public Floor(Integer floorNumber, Building building) {
    this.floorNumber = floorNumber;
    this.building = building;
  }
}
