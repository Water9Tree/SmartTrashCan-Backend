import { Injectable } from '@nestjs/common';
import { BuildingsRepository } from 'src/buildings/buildings.repository';

@Injectable()
export class CansService {
  constructor(private readonly buildingRepository: BuildingsRepository) {}
  createCan({
    buildingNumber,
    floorNumber,
  }: {
    buildingNumber: number;
    floorNumber: number;
  }) {
    console.log(`create can! on ${floorNumber} on ${buildingNumber}`);
    const doc = this.buildingRepository.addCan({
      buildingNumber,
      floorNumber,
    });
    doc.then((res) => {
      console.log(res);
    });
  }
  }
}
