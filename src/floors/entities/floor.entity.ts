import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Can } from '../../cans/entities/can.entity';
import mongoose from 'mongoose';

export type FloorDocument = Floor & Document;

@Schema()
export class Floor {
  @Prop()
  floorNumber: number;

  @Prop([Can])
  trashCans: Can[];
}

export const FloorSchema = SchemaFactory.createForClass(Floor);
