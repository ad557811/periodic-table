import {NgModule} from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressBarModule} from '@angular/material/progress-bar';

@NgModule({
  exports: [MatTableModule, MatPaginatorModule, MatProgressBarModule]
})
export class AngularModule {
}
