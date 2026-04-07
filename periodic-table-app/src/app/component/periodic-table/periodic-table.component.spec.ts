import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PeriodicTableComponent} from './periodic-table.component';
import {HttpService} from '../../service/http.service';
import {of} from 'rxjs';

describe('PeriodicTable', () => {
  let component: PeriodicTableComponent;
  let fixture: ComponentFixture<PeriodicTableComponent>;

  let mockHttpService = {
    getElements: vi.fn(() => of([]))
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PeriodicTableComponent],
      providers: [{provide: HttpService, useValue: mockHttpService}]
    }).compileComponents();

    fixture = TestBed.createComponent(PeriodicTableComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('callService', () => {
    beforeEach(() => {
      vi.resetAllMocks()
    })

    it('should call backend', () => {
      component.callService();
      expect(mockHttpService.getElements).toHaveBeenCalledOnce();
    });
  })
});
