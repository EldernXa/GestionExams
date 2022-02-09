import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Period } from 'src/app/model/period/period';
import { LoginService } from 'src/app/service/login/login.service';
import { PeriodService } from 'src/app/service/period/period-service.service';

@Component({
  selector: 'app-period-form',
  templateUrl: './period-form.component.html',
  styleUrls: ['./period-form.component.css']
})
export class PeriodFormComponent{

  period: Period;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private periodService: PeriodService, private loginService: LoginService){
    this.loginService.verifyIfLogin();
    this.period = new Period();
  }

  onSubmit(){
    this.periodService.savePeriod(this.period).subscribe(result=>{
      this.gotoPeriodList();
    });
  }

  gotoPeriodList(){
    this.router.navigate(['/period']);
  }

}
