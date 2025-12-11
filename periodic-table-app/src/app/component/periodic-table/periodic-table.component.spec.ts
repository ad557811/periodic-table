import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeriodicTableComponent } from './periodic-table.component';

describe('PeriodicTable', () => {
  let component: PeriodicTableComponent;
  let fixture: ComponentFixture<PeriodicTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PeriodicTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PeriodicTableComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
