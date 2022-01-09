import { TestBed } from '@angular/core/testing';

import { PeriodServiceService } from './period-service.service';

describe('PeriodServiceService', () => {
  let service: PeriodServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PeriodServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
