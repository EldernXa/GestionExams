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
  currentYear = new Date().getFullYear();

  constructor(private periodService: PeriodService, private loginService: LoginService) {
    this.loginService.redirectIfNotLogin();
    this.periods = [];

  }

  ngOnInit(){
    this.periodService.findAll().subscribe(data=>{
      this.periods = data;
      this.periods.sort((a,b) => b.year - a.year);
      for(let i = 0; i<this.periods.length; i++){
        this.isDisabled(this.periods[i].id, i);
      }
    });
  }

  deletePeriod(idPeriod: number){
    this.periodService.deletePeriod(idPeriod).subscribe(
      ()=>{
        this.periods = [];
        this.ngOnInit();
      }
    );
  }

  isDisabled(idPeriod: number, idTab: number){
    this.periodService.isDisabled(idPeriod).subscribe((isDisabled)=>{
      this.periods[idTab].isPlanify = isDisabled;
    });
  }

  getToolTipTextDeleteButton(period: Period): string{
    if(period.isPlanify)
      return "La période est déjà planifiée ou terminée!";
    return "";
  }

  getToolTipTextDetailsButton(period: Period): string{
    if(!period.isPlanify && period.year >= this.currentYear)
      return "La période est vide ou n'est pas encore planifiée !";
    return "";
  }

}
