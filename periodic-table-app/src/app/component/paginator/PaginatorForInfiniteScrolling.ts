import {MatPaginatorIntl} from '@angular/material/paginator';
import {Injectable} from '@angular/core';

@Injectable()
export class PaginatorForInfiniteScrolling extends MatPaginatorIntl {

  override getRangeLabel = (page: number, pageSize: number, length: number) => {
    return `Page ${page + 1}`;
  };

}
