import { Body, Controller, Param, Post } from '@nestjs/common';
import { FloorsService } from './floors.service';

@Controller()
export class FloorsController {
  constructor(private readonly floorsService: FloorsService) {}

  @Post('/buildings/:buildingNumber/floors')
  createFloor(
    @Param('buildingNumber') buildingNumber: number,
    @Body('floorNumber') floorNumber: number,
  ) {
    return this.floorsService.createFloor({ buildingNumber, floorNumber });
  }
}
