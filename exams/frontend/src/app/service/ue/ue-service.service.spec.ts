import { TestBed } from '@angular/core/testing';

import { UeService } from './ue-service.service';

describe('UeServiceService', () => {
  let service: UeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
