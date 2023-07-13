import { Module } from '@nestjs/common';
import { CansService } from './cans.service';
import { CansController } from './cans.controller';

@Module({
  providers: [CansService],
  controllers: [CansController],
})
export class CansModule {}
