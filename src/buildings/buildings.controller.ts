import { Body, Controller, Post, UseGuards } from '@nestjs/common';
import { CreateBuildingDto } from './dtos/create-building.dto';
import { BuildingsService } from './buildings.service';
import { AuthGuard } from '@nestjs/passport';
import { RolesGuard } from 'src/auth/passport/role.guard';
import { Roles } from 'src/users/entities/authorities';

@Controller('buildings')
export class BuildingsController {
  constructor(private readonly buildingsService: BuildingsService) {}

  @Post()
  @UseGuards(AuthGuard('jwt'), RolesGuard)
  @Roles(['ROLE_ADMIN'])
  createBuilding(@Body() buildingData: CreateBuildingDto) {
    return this.buildingsService.createBuilding(buildingData);
  }
}
