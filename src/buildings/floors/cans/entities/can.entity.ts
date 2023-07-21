import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { IsNumber } from 'class-validator';
import mongoose from 'mongoose';

export type CanDocument = Can & Document;

class Status {
  @IsNumber()
  regular: number;

  @IsNumber()
  bottle: number;

  @IsNumber()
  plastic: number;

  @IsNumber()
  paper: number;
}

@Schema({ timestamps: { createdAt: 'createdAt', updatedAt: 'updatedAt' } })
export class Can {
  @Prop()
  _id: mongoose.Types.ObjectId;

  @Prop()
  status: Status;

  @Prop({ default: new Date(), type: mongoose.Schema.Types.Date })
  createdAt: Date;

  @Prop({ default: new Date(), type: mongoose.Schema.Types.Date })
  updatedAt: Date;
}

export const CanSchema = SchemaFactory.createForClass(Can);
