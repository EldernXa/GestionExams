import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PeriodListComponent } from './component/period-list/period-list.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PeriodService } from './service/period/period-service.service';
import { PeriodFormComponent } from './component/period-form/period-form.component';
import { PeriodDetailsComponent } from './component/period-details/period-details.component';
import { ExamManagementComponent } from './component/exam-management/exam-management.component';
import { ExamService } from './service/exam/exam.service';

@NgModule({
  declarations: [
    AppComponent,
    PeriodListComponent,
    PeriodFormComponent,
    PeriodDetailsComponent,
    ExamManagementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PeriodService, ExamService],
  bootstrap: [AppComponent]
})
export class AppModule { }
