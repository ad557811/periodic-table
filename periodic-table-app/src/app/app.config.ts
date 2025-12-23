import {ApplicationConfig, inject, provideAppInitializer, provideBrowserGlobalErrorListeners} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {HttpService} from './service/http.service';
import {MatPaginatorIntl} from '@angular/material/paginator';
import {PaginatorForInfiniteScrolling} from './component/paginator/PaginatorForInfiniteScrolling';

export const appConfig: ApplicationConfig = {
  providers: [
    {provide: MatPaginatorIntl, useClass: PaginatorForInfiniteScrolling},
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideAppInitializer(() => {
      let httpClient = inject(HttpService);
      return httpClient.init();
    })
  ]
};
