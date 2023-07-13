import { IsNumber } from 'class-validator';

export class CreateFloorDto {
  @IsNumber()
  readonly buildingNumber: number;

  @IsNumber()
  readonly floorNumber: number;
}
