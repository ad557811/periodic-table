import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeriodicTableDisplay } from './periodic-table-display';

describe('PeriodicTableDisplay', () => {
  let component: PeriodicTableDisplay;
  let fixture: ComponentFixture<PeriodicTableDisplay>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PeriodicTableDisplay]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PeriodicTableDisplay);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
