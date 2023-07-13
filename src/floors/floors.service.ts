import { Injectable } from '@nestjs/common';

@Injectable()
export class FloorsService {
  createFloor({
    buildingNumber,
    floorNumber,
  }: {
    buildingNumber: number;
    floorNumber: number;
  }) {
    console.log(`create floor! ${floorNumber} on ${buildingNumber}`);
  }
}
