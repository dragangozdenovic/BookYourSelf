import { TestBed } from '@angular/core/testing';

import { RentaCarService } from './renta-car.service';

describe('RentaCarService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RentaCarService = TestBed.get(RentaCarService);
    expect(service).toBeTruthy();
  });
});
