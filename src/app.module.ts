import { Module } from '@nestjs/common';
import { CansModule } from './cans/cans.module';
import { BuildingsModule } from './buildings/buildings.module';
import { FloorsModule } from './floors/floors.module';

@Module({
  imports: [CansModule, BuildingsModule, FloorsModule],
})
export class AppModule {}
