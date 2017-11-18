import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingTerminalComponent } from './parking-terminal.component';

describe('ParkingTerminalComponent', () => {
  let component: ParkingTerminalComponent;
  let fixture: ComponentFixture<ParkingTerminalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParkingTerminalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingTerminalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
