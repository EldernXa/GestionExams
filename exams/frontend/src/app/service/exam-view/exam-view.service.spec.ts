import { TestBed } from '@angular/core/testing';

import { ExamViewService } from './exam-view.service';

describe('ExamViewService', () => {
  let service: ExamViewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExamViewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
