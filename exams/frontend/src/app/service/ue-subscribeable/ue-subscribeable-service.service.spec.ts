import { TestBed } from '@angular/core/testing';

import { UeSubscribeableService } from './ue-subscribeable-service.service';

describe('UeSubscribeableService', () => {
  let service: UeSubscribeableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UeSubscribeableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
