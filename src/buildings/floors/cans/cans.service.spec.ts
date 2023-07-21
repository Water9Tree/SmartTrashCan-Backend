import { Test, TestingModule } from '@nestjs/testing';
import { CansService } from './cans.service';

describe('CansService', () => {
  let service: CansService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [CansService],
    }).compile();

    service = module.get<CansService>(CansService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
