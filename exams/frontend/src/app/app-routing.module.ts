import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'
import { CommonModule } from '@angular/common';
import { PeriodListComponent } from './component/period-list/period-list.component';
import { PeriodFormComponent } from './component/period-form/period-form.component';
import { PeriodDetailsComponent } from './component/period-details/period-details.component';
import {GradeListComponent} from "./component/grade-list/grade-list.component";
import {UeListComponent} from "./component/ue-list/ue-list.component";
import {UeFormComponent} from "./component/ue-form/ue-form.component";
import {UeManagementComponent} from "./component/ue-management/ue-management.component";
import { LoginComponent } from './component/login/login.component';
import { ExamsViewComponent } from './component/exams-view/exams-view.component';
import {InscriptionsListComponent} from "./component/inscriptions-list/inscriptions-list.component";
import {UeSubscribeableListComponent} from "./component/ue-subscribeable-list/ue-subscribeable-list.component";
import {GradesViewComponent} from "./component/grades-view/grades-view.component";

const routes: Routes = [
  { path: 'listPeriod', component: ExamsViewComponent},
  { path: 'period', component: PeriodListComponent},
  { path: 'addperiod', component: PeriodFormComponent},
  { path: 'period/:id', component: PeriodDetailsComponent},
  { path: 'grades/exam/:id', component: GradeListComponent},
  { path: 'ue', component: UeListComponent},
  { path: 'addue', component: UeFormComponent},
  { path: 'ueManagement/:id', component: UeManagementComponent},
  { path: 'login', component: LoginComponent},
  { path: 'inscriptions', component: InscriptionsListComponent},
  { path: 'ueSubscribeable', component: UeSubscribeableListComponent},
  { path: 'gradesView', component: GradesViewComponent},
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
