import { Module } from '@nestjs/common';
import { FloorsController } from './floors.controller';
import { FloorsService } from './floors.service';
import { MongooseModule } from '@nestjs/mongoose';
import {
  Building,
  BuildingSchema,
} from 'src/buildings/entities/building.entity';
import { BuildingsRepository } from 'src/buildings/buildings.repository';

@Module({
  imports: [
    MongooseModule.forFeature([
      { name: Building.name, schema: BuildingSchema },
    ]),
  ],
  controllers: [FloorsController],
  providers: [FloorsService, BuildingsRepository],
})
export class FloorsModule {}
