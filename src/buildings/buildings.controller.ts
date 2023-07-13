import { Body, Controller, Post } from '@nestjs/common';
import { CreateBuildingDto } from './dtos/create-building.dto';
import { BuildingsService } from './buildings.service';

@Controller('buildings')
export class BuildingsController {
  constructor(private readonly buildingsService: BuildingsService) {}

  @Post()
  createBuilding(@Body() buildingData: CreateBuildingDto) {
    return this.buildingsService.createBuilding(buildingData);
  }
}
