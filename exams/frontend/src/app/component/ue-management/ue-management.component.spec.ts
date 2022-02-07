import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UeManagementComponent } from './ue-management.component';

describe('UeManagementComponent', () => {
  let component: UeManagementComponent;
  let fixture: ComponentFixture<UeManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UeManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UeManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
