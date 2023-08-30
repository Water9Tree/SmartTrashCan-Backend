import { Injectable } from '@nestjs/common';
import { BuildingsRepository } from './buildings.repository';

@Injectable()
export class BuildingsService {
  constructor(private readonly buildingRepository: BuildingsRepository) {}
  createBuilding({
    buildingNumber,
    buildingName,
  }: {
    buildingNumber: number;
    buildingName: string;
  }) {
    console.log(`create building! ${buildingNumber}, ${buildingName}`);

    this.buildingRepository.createBuilding({
      buildingName,
      buildingNumber,
    });
  }

  getBuildingNumbers() {
    return this.buildingRepository.getAll();
  }
}
