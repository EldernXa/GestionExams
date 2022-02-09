import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'
import { CommonModule } from '@angular/common';
import { PeriodListComponent } from './component/period-list/period-list.component';
import { PeriodFormComponent } from './component/period-form/period-form.component';
import { PeriodDetailsComponent } from './component/period-details/period-details.component';
import { ExamManagementComponent } from './component/exam-management/exam-management.component';
import {GradeListComponent} from "./component/grade-list/grade-list.component";
import {UeListComponent} from "./component/ue-list/ue-list.component";
import {UeFormComponent} from "./component/ue-form/ue-form.component";
import {UeManagementComponent} from "./component/ue-management/ue-management.component";
import { LoginComponent } from './component/login/login.component';

const routes: Routes = [
  { path: 'period', component: PeriodListComponent},
  { path: 'addperiod', component: PeriodFormComponent},
  { path: 'period/:id', component: PeriodDetailsComponent},
  { path: 'periodManagement/:id', component: ExamManagementComponent},
  { path: 'grades/exam/:id', component: GradeListComponent},
  { path: 'ue', component: UeListComponent},
  { path: 'addue', component: UeFormComponent},
  { path: 'ueManagement/:id', component: UeManagementComponent},
  { path: 'login', component: LoginComponent},
  //{ path: '**', component: PageNotFoundComponent },  // TODO Wildcard route for a 404 page
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
