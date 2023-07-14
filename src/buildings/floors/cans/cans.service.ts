import { Injectable } from '@nestjs/common';

@Injectable()
export class CansService {
  createCan({
    buildingNumber,
    floorNumber,
  }: {
    buildingNumber: number;
    floorNumber: number;
  }) {
    console.log(`create can! on ${floorNumber} on ${buildingNumber}`);
  }
}
