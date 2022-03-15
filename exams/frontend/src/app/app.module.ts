import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PeriodListComponent } from './component/period-list/period-list.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PeriodService } from './service/period/period-service.service';
import { PeriodFormComponent } from './component/period-form/period-form.component';
import { PeriodDetailsComponent } from './component/period-details/period-details.component';
import { ExamManagementComponent } from './component/exam-management/exam-management.component';
import { ExamService } from './service/exam/exam.service';
import { GradeListComponent } from './component/grade-list/grade-list.component';
import {GradeService} from "./service/grade/grade-service.service";
import { UeListComponent } from './component/ue-list/ue-list.component';
import {UeService} from "./service/ue/ue-service.service";
import { UeFormComponent } from './component/ue-form/ue-form.component';
import { UeManagementComponent } from './component/ue-management/ue-management.component';
import { LoginComponent } from './component/login/login.component';
import { LoginService } from './service/login/login.service';
import { ExamsViewComponent } from './component/exams-view/exams-view.component';
import { ExamViewService } from './service/exam-view/exam-view.service';
import { AuthInterceptor } from './AuthInterceptor';
import { Router } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    PeriodListComponent,
    PeriodFormComponent,
    PeriodDetailsComponent,
    ExamManagementComponent,
    GradeListComponent,
    UeListComponent,
    UeFormComponent,
    UeManagementComponent,
    LoginComponent,
    ExamsViewComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PeriodService, ExamService, GradeService, UeService, LoginService, ExamViewService,
    {
      provide: HTTP_INTERCEPTORS,
      useFactory: function(router: Router, loginService: LoginService){
        return new AuthInterceptor(router, loginService);
      },
      multi: true,
      deps: [Router, LoginService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
