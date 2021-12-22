import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'
import { CommonModule } from '@angular/common';
import { PeriodListComponent } from './component/period-list/period-list.component';
import { PeriodFormComponent } from './component/period-form/period-form.component';
import { PeriodDetailsComponent } from './component/period-details/period-details.component';
import { ExamManagementComponent } from './component/exam-management/exam-management.component';

const routes: Routes = [
  { path: 'period', component: PeriodListComponent},
  { path: 'addperiod', component: PeriodFormComponent},
  { path: 'period/:id', component: PeriodDetailsComponent},
  { path: 'periodManagement/:id', component: ExamManagementComponent},
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
