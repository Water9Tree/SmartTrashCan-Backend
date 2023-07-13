import { Test, TestingModule } from '@nestjs/testing';
import { CansController } from './cans.controller';

describe('CansController', () => {
  let controller: CansController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [CansController],
    }).compile();

    controller = module.get<CansController>(CansController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
