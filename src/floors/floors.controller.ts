import { Body, Controller, Post } from '@nestjs/common';
import { CreateFloorDto } from './dtos/create-floor.dto';
import { FloorsService } from './floors.service';

@Controller('floors')
export class FloorsController {
  constructor(private readonly floorsService: FloorsService) {}

  @Post()
  createFloor(@Body() floorData: CreateFloorDto) {
    return this.floorsService.createFloor(floorData);
  }
}
