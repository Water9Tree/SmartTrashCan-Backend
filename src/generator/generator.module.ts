import { Module } from '@nestjs/common';
import { GeneratorService } from './generator.service';
import { ScheduleModule } from '@nestjs/schedule';
import { MongooseModule } from '@nestjs/mongoose';
import {
  Building,
  BuildingSchema,
} from '../buildings/entities/building.entity';

@Module({
  imports: [
    ScheduleModule.forRoot(),
    MongooseModule.forFeature([
      { name: Building.name, schema: BuildingSchema },
    ]),
  ],
  providers: [GeneratorService],
})
export class GeneratorModule {}
