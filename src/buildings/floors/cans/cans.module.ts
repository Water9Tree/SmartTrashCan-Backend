import { Module } from '@nestjs/common';
import { CansService } from './cans.service';
import { CansController } from './cans.controller';
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
  controllers: [CansController],
  providers: [CansService, BuildingsRepository],
})
export class CansModule {}
