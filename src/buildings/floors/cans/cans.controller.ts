import { Controller, Get, Param, Post, UseGuards } from '@nestjs/common';
import { CansService } from './cans.service';
import { AuthGuard } from '@nestjs/passport';
import { RolesGuard } from 'src/auth/passport/role.guard';
import { Roles } from 'src/users/entities/authorities';
import { ApiOperation } from '@nestjs/swagger';

@Controller()
export class CansController {
  constructor(private readonly cansService: CansService) {}

  @Post('/buildings/:buildingNumber/floors/:floorNumber/can')
  @UseGuards(AuthGuard('jwt'), RolesGuard)
  @Roles(['ROLE_ADMIN'])
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
