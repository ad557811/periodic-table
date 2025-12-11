import { Routes } from '@angular/router';
import {PeriodicTableComponent} from './component/periodic-table/periodic-table.component';

export const routes: Routes = [
  {path: 'periodic-table', component: PeriodicTableComponent},
  {path: '', redirectTo: '/periodic-table', pathMatch: 'full'},
];
