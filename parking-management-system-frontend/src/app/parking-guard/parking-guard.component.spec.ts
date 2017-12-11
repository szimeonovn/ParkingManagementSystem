import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingGuardComponent } from './parking-guard.component';

describe('ParkingGuardComponent', () => {
  let component: ParkingGuardComponent;
  let fixture: ComponentFixture<ParkingGuardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParkingGuardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingGuardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
