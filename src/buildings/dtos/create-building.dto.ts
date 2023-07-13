import { IsString, IsNumber } from 'class-validator';

export class CreateBuildingDto {
  @IsNumber()
  readonly buildingNumber: number;

  @IsString()
  readonly buildingName: string;
}
