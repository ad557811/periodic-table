import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

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

  getElements(pageSize: number, pageIndex: number): Observable<Element[]> {
    let endpoint = `${this.backend_url}/element?max=${pageSize}&page=${pageIndex}`;
    return this.httpClient.get<Element[]>(endpoint);
  }

}
