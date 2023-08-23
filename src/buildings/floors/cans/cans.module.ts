import { Module } from '@nestjs/common';
import { CansService } from './cans.service';
import { CansController } from './cans.controller';
import { MongooseModule } from '@nestjs/mongoose';
import {
  Building,
  BuildingSchema,
} from 'src/buildings/entities/building.entity';
import { BuildingsRepository } from 'src/buildings/buildings.repository';
import { User, UserSchema } from '../../../users/entities/user.entity';
import { UsersRepository } from '../../../users/users.repository';

@Module({
  imports: [
    MongooseModule.forFeature([
      { name: Building.name, schema: BuildingSchema },
      { name: User.name, schema: UserSchema },
    ]),
  ],
  controllers: [CansController],
  providers: [CansService, BuildingsRepository, UsersRepository],
})
export class CansModule {}
