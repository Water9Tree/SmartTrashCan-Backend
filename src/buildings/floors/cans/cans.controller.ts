import { Controller, Param, Post } from '@nestjs/common';
import { CansService } from './cans.service';

@Controller()
export class CansController {
  constructor(private readonly cansService: CansService) {}

  @Post('/buildings/:buildingNumber/floors/:floorNumber/cans')
  createTrashCan(
    @Param('buildingNumber') buildingNumber: number,
    @Param('floorNumber') floorNumber: number,
  ) {
    return this.cansService.createCan({ buildingNumber, floorNumber });
  }
}
