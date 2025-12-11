import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {AngularModule} from '../../model/angular.module';
import {HttpClient} from '@angular/common/http';
import {HttpService} from '../../service/http.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-periodic-table',
  imports: [AngularModule],
  templateUrl: './periodic-table.component.html',
  styleUrl: './periodic-table.component.scss',
})
export class PeriodicTableComponent implements AfterViewInit {

  columnNames = ["number", "name", "appearance", "category", "density", "discoveredBy", "namedBy", "symbol", "summary"];

  columns;
  dataSource: Element[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private httpClient: HttpService) {
    this.columns = this.columnNames.map(columnName => {
      return {
        columnDef: columnName,
        header: columnName
      }
    })
  }

  ngAfterViewInit(): void {
    this.paginator.page.subscribe((pageChange) => {
      this.httpClient.getElements(pageChange.pageSize, pageChange.pageIndex)
    });
  }
}
