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
import { AuthGuard } from './service/auth/auth-guard.service';

const routes: Routes = [
  { path: 'listPeriod', component: ExamsViewComponent, canActivate: [AuthGuard], data:{ role: 'STUDENT'}},
  { path: 'period', component: PeriodListComponent, canActivate:[AuthGuard], data:{role: 'ADMIN'}},
  { path: 'addperiod', component: PeriodFormComponent, canActivate:[AuthGuard], data:{role: 'ADMIN'}},
  { path: 'period/:id', component: PeriodDetailsComponent, canActivate:[AuthGuard], data:{role: 'ADMIN'}},
  { path: 'grades/exam/:id', component: GradeListComponent, canActivate:[AuthGuard], data:{role: 'ADMIN'}},
  { path: 'ue', component: UeListComponent, canActivate:[AuthGuard], data:{role: 'ADMIN'}},
  { path: 'addue', component: UeFormComponent, canActivate:[AuthGuard], data:{role: 'ADMIN'}},
  { path: 'ueManagement/:id', component: UeManagementComponent, canActivate:[AuthGuard], data:{role: 'ADMIN'}},
  { path: 'login', component: LoginComponent},
  { path: 'inscriptions', component: InscriptionsListComponent, canActivate:[AuthGuard], data:{role: 'STUDENT'}},
  { path: 'ueSubscribeable', component: UeSubscribeableListComponent, canActivate:[AuthGuard], data:{role: 'STUDENT'}},
  { path: 'gradesView', component: GradesViewComponent, canActivate:[AuthGuard], data:{role: 'STUDENT'}},
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
