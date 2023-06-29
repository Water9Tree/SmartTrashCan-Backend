package com.water9tree.trashcan.can.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "can")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Can {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "regular", nullable = false)
  private Integer regular;

  @Column(name = "bottle", nullable = false)
  private Integer bottle;

  @Column(name = "plastic", nullable = false)
  private Integer plastic;

  @Column(name = "paper", nullable = false)
  private Integer paper;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "floor_id")
  private Floor floor;

  public void changeValue(Integer regular, Integer bottle, Integer plastic, Integer paper) {
    this.regular = regular;
    this.bottle = bottle;
    this.plastic = plastic;
    this.paper = paper;
  }

  @Builder
  public Can(Integer regular, Integer bottle, Integer plastic, Integer paper, Floor floor) {
    this.regular = regular;
    this.bottle = bottle;
    this.plastic = plastic;
    this.paper = paper;
    this.floor = floor;
  }
}
