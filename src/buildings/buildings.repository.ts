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

  async addFloor({
    buildingNumber,
    floorNumber,
  }: {
    buildingNumber: number;
    floorNumber: number;
  }) {
    try {
      const buildingDoc = await this.buildingModel.findOneAndUpdate(
        { buildingNumber: buildingNumber },
        {
          $push: {
            floors: {
              floorNumber: floorNumber,
              trashCans: [],
            },
          },
        },
      );
      return buildingDoc.toObject();
    } catch (err) {
      console.log('error...');
      console.error(err);
    }
  }

  async addCan({
    buildingNumber,
    floorNumber,
  }: {
    buildingNumber: number;
    floorNumber: number;
  }) {
    try {
      const buildingDoc = await this.buildingModel.findOneAndUpdate(
        { buildingNumber: buildingNumber, 'floors.floorNumber': floorNumber },
        {
          $push: {
            'floors.$.trashCans': {
              status: {
                regular: null,
                bottle: null,
                plastic: null,
                paper: null,
              },
            },
          },
        },
      );
      console.log(
        'asdf',
        this.buildingModel.findOne(
          { buildingNumber: buildingNumber },
          {
            floors: { $elemMatch: { floorNumber: floorNumber } },
          },
        ),
      );
      return buildingDoc.toObject();
    } catch (err) {
      console.log('error...');
      console.error(err);
    }
  }
}
