import { Body, Controller, Get, Post } from '@nestjs/common';
import { CreateBuildingDto } from './dtos/create-building.dto';
import { BuildingsService } from './buildings.service';
import { ApiOperation } from '@nestjs/swagger';

@Controller('buildings')
export class BuildingsController {
  constructor(private readonly buildingsService: BuildingsService) {}

  @Post()
  createBuilding(@Body() buildingData: CreateBuildingDto) {
    return this.buildingsService.createBuilding(buildingData);
  }

  @Get()
  @ApiOperation({ summary: '모든 건물 조회' })
  getBuildingNumbers() {
    return this.buildingsService.getBuildingNumbers();
  }
}
