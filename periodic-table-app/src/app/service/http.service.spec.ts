import {TestBed} from '@angular/core/testing';

import {HttpService} from './http.service';
import {HttpClient} from '@angular/common/http';

describe('HttpClient', () => {
  let service: HttpService;

  let mockHttpClient = {
    get: vi.fn()
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [{provide: HttpClient, useValue: mockHttpClient}]
    });

    service = TestBed.inject(HttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  afterEach(() => {
    vi.resetAllMocks()
  })

  describe('init', () => {
    beforeAll(() => {
      let config = {backend_url: 'https://my-actual-server.com'}
      let fetch = vi.fn(() => Promise.resolve(config));

      vi.stubGlobal('fetch', fetch);
    })

    it("should NOT call localhost", async () => {
      await service.init();
      service.getElements(10, 1);
      expect(mockHttpClient.get).toHaveBeenCalledExactlyOnceWith('https://my-actual-server.com/element?max=10&page=1');
    })

    afterAll(() => {
      vi.unstubAllGlobals();
    })
  })

  describe('getElements', () => {
    it('should call with pageSize and pageIndex', () => {
      service.getElements(10, 1);
      expect(mockHttpClient.get).toHaveBeenCalledExactlyOnceWith("http://localhost:8080/element?max=10&page=1");
    });

    it('should call with pageSize, pageIndex and queryString', () => {
      service.getElements(10, 1, "hydrogen");
      expect(mockHttpClient.get).toHaveBeenCalledExactlyOnceWith("http://localhost:8080/element?max=10&page=1&query=hydrogen");
    });
  });
});
