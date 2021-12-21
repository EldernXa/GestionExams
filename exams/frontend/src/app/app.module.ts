import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PeriodListComponent } from './component/period-list/period-list.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PeriodService } from './service/period-service.service';
import { PeriodFormComponent } from './component/period-form/period-form.component';

@NgModule({
  declarations: [
    AppComponent,
    PeriodListComponent,
    PeriodFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PeriodService],
  bootstrap: [AppComponent]
})
export class AppModule { }
