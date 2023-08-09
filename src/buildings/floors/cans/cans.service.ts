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

  getCans(buildingNumber: number) {
    console.log(`get cans! on ${buildingNumber} building`);
    const buildings = this.buildingRepository.getCans(buildingNumber);
    return buildings
      .then((res) => {
        return res.map(({ buildingNumber, buildingName, floors }) => ({
          buildingNumber,
          buildingName,
          floors: floors.map(({ floorNumber, trashCan }) => ({
            floorNumber,
            trashCan,
          })),
        }));
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
