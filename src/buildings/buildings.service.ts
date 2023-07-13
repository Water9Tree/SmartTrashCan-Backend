import { Injectable } from '@nestjs/common';

@Injectable()
export class BuildingsService {
  createBuilding({
    buildingNumber,
    buildingName,
  }: {
    buildingNumber: number;
    buildingName: string;
  }) {
    console.log(`create building! ${buildingNumber}, ${buildingName}`);
  }
}
