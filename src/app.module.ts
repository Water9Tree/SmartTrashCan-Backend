import { Module } from '@nestjs/common';
import { CansModule } from './buildings/floors/cans/cans.module';
import { BuildingsModule } from './buildings/buildings.module';
import { FloorsModule } from './buildings/floors/floors.module';
import { MongooseModule } from '@nestjs/mongoose';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { AuthModule } from './auth/auth.module';
import { UsersModule } from './users/users.module';
import { GeneratorModule } from './generator/generator.module';

@Module({
  imports: [
    CansModule,
    BuildingsModule,
    FloorsModule,
    ConfigModule.forRoot({
      cache: true,
      isGlobal: true,
    }),
    MongooseModule.forRootAsync({
      imports: [ConfigModule],
      useFactory: async (configService: ConfigService) => ({
        uri: configService.get('MONGODB_URL'),
      }),
      inject: [ConfigService],
    }),
    UsersModule,
    AuthModule,
    GeneratorModule,
  ],
})
export class AppModule {}
