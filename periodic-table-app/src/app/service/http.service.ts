import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PeriodicTableElement} from '../model/element.model';

@Injectable({
  providedIn: 'root',
})
export class HttpService {

  private backend_url: string = "http://localhost:8080";

  constructor(private readonly httpClient: HttpClient) {
  }

  init() {
    return fetch('/config')
      .then((data: any) => {
        if (data.backend_url) {
          this.backend_url = data.backend_url;
        }
      });
  }

  getElements(queryString: string, pageSize: number, pageIndex: number): Observable<PeriodicTableElement[]> {
    let endpoint = `${this.backend_url}/element?max=${pageSize}&page=${pageIndex}`;
    if (queryString && queryString.length > 0) {
      endpoint += `&query=${queryString}`
    }
    return this.httpClient.get<PeriodicTableElement[]>(endpoint);
  }

}
