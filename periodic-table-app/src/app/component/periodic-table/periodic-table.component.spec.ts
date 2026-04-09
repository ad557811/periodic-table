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

    it('should call backend with default values', () => {
      component.callService();
      expect(mockHttpService.getElements).toHaveBeenCalledExactlyOnceWith(10, 0, '');
    });
  })

  describe('applyFilter', () => {
    beforeEach(() => {
      vi.resetAllMocks()
    })

    it('should reset paginator and call service', () => {
      let spyOnPaginator = vi.spyOn(component.paginator, 'firstPage');
      let spyOnService = vi.spyOn(mockHttpService, 'getElements');
      component.applyFilter();

      expect(spyOnPaginator).toHaveBeenCalledOnce();
      expect(spyOnService).toHaveBeenCalledOnce();
    });
  })
});
