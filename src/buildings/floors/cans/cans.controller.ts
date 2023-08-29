import { Controller, Get, Param, Post } from '@nestjs/common';
import { CansService } from './cans.service';
import { ApiOperation } from '@nestjs/swagger';

@Controller()
export class CansController {
  constructor(private readonly cansService: CansService) {}

  @Post('/buildings/:buildingNumber/floors/:floorNumber/can')
  createTrashCan(
    @Param('buildingNumber') buildingNumber: number,
    @Param('floorNumber') floorNumber: number,
  ) {
    return this.cansService.createCan({ buildingNumber, floorNumber });
  }

  @Get('/buildings/:buildingNumber')
  @ApiOperation({ summary: '쓰레기통 조회' })
  getCans(@Param('buildingNumber') buildingNumber: number) {
    return this.cansService.getCans(buildingNumber);
  }
}
