import { Floor } from '../../floors/entities/floor.entity';

export class Building {
  buildingNumber: number;
  buildingName: string;
  floors: Floor[];
}
