import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {AngularModule} from '../../model/angular.module';
import {HttpService} from '../../service/http.service';
import {MatPaginator} from '@angular/material/paginator';
import {map, startWith, switchMap} from 'rxjs';

@Component({
  selector: 'app-periodic-table',
  imports: [AngularModule],
  templateUrl: './periodic-table.component.html',
  styleUrl: './periodic-table.component.scss',
})
export class PeriodicTableComponent implements AfterViewInit {

  loading = true;
  maxElements = Infinity;
  columnNames = ["number", "name", "appearance", "category", "density", "discoveredBy", "namedBy", "symbol", "summary"];

  columns;
  dataSource: Element[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private readonly httpClient: HttpService) {
    this.columns = this.columnNames.map(columnName => {
      return {
        columnDef: columnName,
        header: columnName
      }
    })
  }

  ngAfterViewInit(): void {
    this.paginator.page.pipe(
      startWith([]),
      switchMap(() => {
        this.loading = true;
        return this.httpClient.getElements(this.paginator.pageSize, this.paginator.pageIndex)
      }),
      map(result => {
        this.loading = false;
        return result;
      })
    ).subscribe(result => this.dataSource = result);
  }
}
