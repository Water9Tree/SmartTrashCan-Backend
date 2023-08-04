import { Injectable, Logger } from '@nestjs/common';
import { Cron, CronExpression } from '@nestjs/schedule';
import { InjectModel } from '@nestjs/mongoose';
import {
  Building,
  BuildingDocument,
} from '../buildings/entities/building.entity';
import { Model } from 'mongoose';

@Injectable()
export class GeneratorService {
  private readonly logger = new Logger(GeneratorService.name);

  constructor(
    @InjectModel(Building.name) private buildingModel: Model<BuildingDocument>,
  ) {}

  @Cron(CronExpression.EVERY_SECOND)
  handleCron() {
    this.logger.debug('Called when every second!');
    this.buildingModel.find({}).then(async (buildings) => {
      if (buildings.length == 0) return;

      const randomBuildingNumber = Math.floor(Math.random() * buildings.length);
      if (buildings[randomBuildingNumber]?.floors == null) return;
      const randomFloors = buildings[randomBuildingNumber].floors;

      const randomFloorNumber = Math.floor(Math.random() * randomFloors.length);
      if (randomFloors[randomFloorNumber]?.trashCan == null) return;
      const randomTrashCanStatus =
        randomFloors[randomFloorNumber].trashCan.status;

      console.log(
        buildings[randomBuildingNumber]?.floors.map((i) => i.trashCan),
      );
      await this.buildingModel.findOneAndUpdate(
        {
          buildingNumber: buildings[randomBuildingNumber].buildingNumber,
          'floors.floorNumber': randomFloors[randomFloorNumber].floorNumber,
        },
        {
          $set: {
            'floors.$.trashCan.status': {
              regular: this.changeTrashCanRandomly(
                randomTrashCanStatus.regular,
              ),
              bottle: this.changeTrashCanRandomly(randomTrashCanStatus.bottle),
              plastic: this.changeTrashCanRandomly(
                randomTrashCanStatus.plastic,
              ),
              paper: this.changeTrashCanRandomly(randomTrashCanStatus.paper),
            },
          },
        },
      );
    });
  }

  changeTrashCanRandomly(percentage: number): number {
    if (percentage == 100) {
      if (Math.floor(Math.random() * 10) == 1)
        return 0; // 10% 확률로 쓰레기통 비움
      else {
        return percentage;
      }
    } else if (80 < percentage && percentage < 100) {
      if (Math.floor(Math.random() * 10) == 1) return 0; // 10% 확률로 쓰레기통 비움
    }
    return Math.floor(Math.random() * 2) == 0 ? percentage + 1 : percentage; // 50% 확률로 쓰레기 채워짐
  }
}
