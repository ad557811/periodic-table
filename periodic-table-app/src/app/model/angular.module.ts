import {NgModule} from '@angular/core';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  exports: [MatTableModule]
})
export class AngularModule {

  displayedColumns: string[] = [
    'number',
    'name',
    'symbol',
    ''];

}
