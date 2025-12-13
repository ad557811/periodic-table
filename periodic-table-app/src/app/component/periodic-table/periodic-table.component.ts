import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import {AngularModule} from '../../angular.module';
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

  pageSize = 10;
  maxElements = Infinity; // enable infinite pagination
  columnNames = ["number", "name", "appearance", "category", "density", "discoveredBy", "namedBy", "symbol", "summary"];

  columns;
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild('searchFilter') searchField!: ElementRef;

  constructor(private readonly httpClient: HttpService) {
    this.columns = this.columnNames.map(columnName => {
      return {
        columnDef: columnName,
        header: columnName
      }
    })
  }

  applyFilter() {
    this.paginator.firstPage();
    this.callService()
      .subscribe(result => this.dataSource.data = result);
  }

  ngAfterViewInit(): void {
    // fill table at startup
    this.callService()
      .subscribe(result => this.dataSource.data = result);

    // refresh at every page change
    this.paginator.page.pipe(
      switchMap(() => this.callService())
    ).subscribe(result => this.dataSource.data = result);
  }

  public callService() {
    return this.httpClient.getElements(this.searchField.nativeElement.value, this.paginator.pageSize, this.paginator.pageIndex);
  }

}
