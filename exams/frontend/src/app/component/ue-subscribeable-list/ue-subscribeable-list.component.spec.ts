import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UeSubscribeableListComponent } from './ue-subscribeable-list.component';

describe('UeSubscribeableListComponent', () => {
  let component: UeSubscribeableListComponent;
  let fixture: ComponentFixture<UeSubscribeableListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UeSubscribeableListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UeSubscribeableListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
