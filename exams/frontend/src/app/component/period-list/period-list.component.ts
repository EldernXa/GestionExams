import { Component, OnInit } from '@angular/core';
import { Period } from 'src/app/model/period/period';
import { LoginService } from 'src/app/service/login/login.service';
import { PeriodService } from 'src/app/service/period/period-service.service';

@Component({
  selector: 'app-period-list',
  templateUrl: './period-list.component.html',
  styleUrls: ['./period-list.component.css']
})
export class PeriodListComponent implements OnInit {

  periods: Period[];

  constructor(private periodService: PeriodService, private loginService: LoginService) {
    this.loginService.redirectIfNotLogin();
    this.periods = [];
    this.periodService.findAll().subscribe(data=>{
      this.periods = data;
    });
  }

  ngOnInit(){
    this.periodService.findAll().subscribe(data=>{
      this.periods = data;
    });
  }

}
