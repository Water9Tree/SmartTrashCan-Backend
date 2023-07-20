import { IsString, IsNumber } from 'class-validator';

export class CreateBuildingDto {
  @IsNumber()
  readonly buildingNumber: number;

  @IsString()
  readonly buildingName: string;

  status: {
    regular: number;
    bottle: number;
    plastic: number;
    paper: number;
  };
}
