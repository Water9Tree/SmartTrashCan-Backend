import { Injectable } from '@nestjs/common';
import { BuildingsRepository } from 'src/buildings/buildings.repository';

@Injectable()
export class FloorsService {
  constructor(private readonly buildingRepository: BuildingsRepository) {}

  createFloor({
    buildingNumber,
    floorNumber,
  }: {
    buildingNumber: number;
    floorNumber: number;
  }) {
    console.log(`create floor! ${floorNumber} on ${buildingNumber}`);
    const doc = this.buildingRepository.addFloor({
      buildingNumber,
      floorNumber,
    });
    doc.then((res) => {
      console.log(res);
    });
  }

  deleteFloor({
    buildingNumber,
    floorNumber,
  }: {
    buildingNumber: number;
    floorNumber: number;
  }) {
    console.log(`delete floor! ${floorNumber} on ${buildingNumber}`);
    const doc = this.buildingRepository.deleteFloor({
      buildingNumber,
      floorNumber,
    });
    doc.then((res) => {
      console.log(res);
    });
  }
}
