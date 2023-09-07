import {
  Body,
  Controller,
  Delete,
  Param,
  Post,
  UseGuards,
} from '@nestjs/common';
import { FloorsService } from './floors.service';
import { ApiBody } from '@nestjs/swagger';
import { AuthGuard } from '@nestjs/passport';
import { RolesGuard } from 'src/auth/passport/role.guard';
import { Roles } from 'src/users/entities/authorities';

@Controller()
export class FloorsController {
  constructor(private readonly floorsService: FloorsService) {}

  @ApiBody({
    schema: {
      properties: {
        floorNumber: { type: 'number' },
      },
    },
  })
  @Post('/buildings/:buildingNumber/floors')
  @UseGuards(AuthGuard('jwt'), RolesGuard)
  @Roles(['ROLE_ADMIN'])
  createFloor(
    @Param('buildingNumber') buildingNumber: number,
    @Body('floorNumber') floorNumber: number,
  ) {
    return this.floorsService.createFloor({ buildingNumber, floorNumber });
  }

  @Delete('/buildings/:buildingNumber/floors/:floorNumber/can')
  @UseGuards(AuthGuard('jwt'), RolesGuard)
  @Roles(['ROLE_ADMIN'])
  deleteFloor(
    @Param('buildingNumber') buildingNumber: number,
    @Param('floorNumber') floorNumber: number,
  ) {
    return this.floorsService.deleteFloor({ buildingNumber, floorNumber });
  }
}
