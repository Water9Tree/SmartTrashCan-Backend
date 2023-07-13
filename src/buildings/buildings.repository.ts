import { Injectable } from '@nestjs/common';
import { Building, BuildingDocument } from './entities/building.entity';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';

@Injectable()
export class BuildingsRepository {
  constructor(
    @InjectModel(Building.name) private buildingModel: Model<BuildingDocument>,
  ) {}

  createBuilding({
    buildingName,
    buildingNumber,
  }: {
    buildingNumber: number;
    buildingName: string;
  }): Promise<BuildingDocument> {
    try {
      const result = new this.buildingModel({
        buildingName,
        buildingNumber,
      });
      return result.save();
    } catch (err) {
      console.log('error...');
    }
  }
}
