import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {AngularModule} from '../../model/angular.module';
import {HttpService} from '../../service/http.service';
import {MatPaginator} from '@angular/material/paginator';
import {switchMap} from 'rxjs';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-periodic-table',
  imports: [AngularModule],
  templateUrl: './periodic-table.component.html',
  styleUrl: './periodic-table.component.scss',
})
export class PeriodicTableComponent implements AfterViewInit {

  loading = true;
  pageSize = 10;
  maxElements = Infinity; // enable infinite pagination
  columnNames = ["number", "name", "appearance", "category", "density", "discoveredBy", "namedBy", "symbol", "summary"];

  columns;
  dataSource = new MatTableDataSource();

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
    this.callService(this.pageSize, 0)
      .subscribe(result => this.dataSource.data = result);

    this.paginator.page.pipe(
      switchMap(() => {
        this.loading = true;
        return this.callService(this.paginator.pageSize, this.paginator.pageIndex)
      })
    ).subscribe({
      next: (result: any) => {
        this.dataSource.data = result;
      },
      error: (err: any) => {
        console.log(err);
      },
      complete: () => {
        this.loading = false;
      }
    });
  }

  public callService(pageSize: number, pageIndex: number) {
    return this.httpClient.getElements(pageSize, pageIndex);
  }

}
