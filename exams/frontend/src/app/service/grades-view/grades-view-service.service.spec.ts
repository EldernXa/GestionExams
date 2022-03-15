import { TestBed } from '@angular/core/testing';

import { GradesViewService } from './grades-view-service.service';

describe('GradesViewService', () => {
  let service: GradesViewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GradesViewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
