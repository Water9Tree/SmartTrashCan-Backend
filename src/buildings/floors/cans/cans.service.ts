import { Injectable } from '@nestjs/common';
import { BuildingsRepository } from 'src/buildings/buildings.repository';
import { UsersRepository } from '../../../users/users.repository';
import { Cron, CronExpression } from '@nestjs/schedule';

@Injectable()
export class CansService {
  constructor(
    private readonly buildingRepository: BuildingsRepository,
    private readonly userRepository: UsersRepository,
  ) {}

  createCan({
    buildingNumber,
    floorNumber,
  }: {
    buildingNumber: number;
    floorNumber: number;
  }) {
    console.log(`create can! on ${floorNumber} on ${buildingNumber}`);
    const doc = this.buildingRepository.addCan({
      buildingNumber,
      floorNumber,
    });
    doc.then((res) => {
      console.log(res);
    });
  }

  getCans(buildingNumber: number) {
    console.log(`get cans! on ${buildingNumber} building`);
    const buildings = this.buildingRepository.getCans(buildingNumber);
    return buildings
      .then((res) => {
        return res.map(({ buildingNumber, buildingName, floors }) => ({
          buildingNumber,
          buildingName,
          floors: floors.map(({ floorNumber, trashCan }) => ({
            floorNumber,
            trashCan,
          })),
        }));
      })
      .catch((err) => {
        console.log(err);
      });
  }

  @Cron(CronExpression.EVERY_5_SECONDS)
  handleCron() {
    console.log('Called when the current second is 5');
    this.userRepository.getByRole('ROLE_CLEANER').then((users) => {
      this.buildingRepository.getAll().then((buildings) => {
        for (const building of buildings) {
          for (const floor of building.floors) {
            for (const user of users) {
              if (user.expoToken) {
                fetch('https://exp.host/--/api/v2/push/send', {
                  method: 'POST',
                  headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                  },
                  body: JSON.stringify({
                    to: user.expoToken,
                    title: `이건 테스트입니다`,
                    body: String(floor.trashCan),
                  }),
                })
                  .then(() => console.log('send!'))
                  .catch((err) => console.log(err));
                console.log(user);
              }
            }
          }
        }
      });
    });
  }
}
