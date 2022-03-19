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
  nameVerif = true;
  beginDatePeriodVerif = true;
  endDatePeriodVerif = true;
  msgNamePeriod = "";
  msgBeginDatePeriod = "";
  msgEndDatePeriod = "";

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private periodService: PeriodService, private loginService: LoginService){
    this.loginService.redirectIfNotLogin();
    this.period = new Period();
  }

  onSubmit(){
    let verifySubmit = true;
    if(!this.verifyBeginDatePeriod()){
      verifySubmit = false;
    }
    if(!this.verifyEndDatePeriod()){
      verifySubmit = false;
    }


    if(!this.verifyNamePeriod()){
      verifySubmit = false;
    }
    if(this.period.beginDatePeriod === '' || this.period.endDatePeriod === '' || this.period.name === ''){
      if(verifySubmit){
        this.periodService.savePeriod(this.period).subscribe(result=>{
          this.gotoPeriodList();
        });
      }
    }else{
      if(verifySubmit){
        this.periodService.isPeriodDateGood(this.period.beginDatePeriod, this.period.endDatePeriod).subscribe(
          (data) => {
            if(!this.verifyDate(Number(data))){
              verifySubmit = false;
            }
            if(verifySubmit){
              this.periodService.isPeriodNameGood(this.period.name).subscribe(
                (isNameGood) =>{
                  
                  if(!this.verifyNamePeriodGood(Boolean(isNameGood))){
                    verifySubmit = false;
                    this.msgNamePeriod = "le nom n'est pas bon";
                    this.nameVerif = true;
                  }

                  if(verifySubmit){
                    this.periodService.savePeriod(this.period).subscribe(result=>{
                      this.gotoPeriodList();
                    });

                  }
                }
              );
            }
          }
        );
      }
    }
    
  }

  verifyNamePeriodGood(data: boolean){
    if(!data){
      this.nameVerif = false;
      this.msgNamePeriod = "Ce nom de période est déjà pris."
      return false;
    }

    this.nameVerif = true;
    this.msgNamePeriod = "";
    return true;
  }

  verifyDate(data:number){
    if(data === 1){
      this.beginDatePeriodVerif = false;
      this.msgBeginDatePeriod = "Cette date est déjà comprise par une autre période."
      return false;
    }
    if(data === 2){
      this.endDatePeriodVerif = false;
      this.msgEndDatePeriod = "Cette date est déjà comprise par une autre période."
      return false;
    }

    this.beginDatePeriodVerif = true;
    this.msgBeginDatePeriod = "";
    this.endDatePeriodVerif = true;
    this.msgEndDatePeriod = "";
    return true;
  }

  verifyNamePeriod(){
    if(this.period.name === ''){
      this.nameVerif = false;
      this.msgNamePeriod = "La nouvelle période doit posséder un nom.";
      return false;
    }

    this.nameVerif = true;
    this.msgNamePeriod = "";
    return true;
  }

  verifyBeginDatePeriod(){
    if(this.period.beginDatePeriod === '')
    {
      this.beginDatePeriodVerif = false;
      this.msgBeginDatePeriod = "Vous devez indiquer une date de début de période."
      return false;
    }

    let myDate = new Date();
    let tabCurrentDate = myDate.toLocaleDateString().split('/');
    let tabBeginDatePeriod = this.period.beginDatePeriod.split('-');
    console.log(tabBeginDatePeriod + " -- " + tabCurrentDate);
    if(tabBeginDatePeriod[0]<tabCurrentDate[2] || (tabBeginDatePeriod[0]=== tabCurrentDate[2] && tabBeginDatePeriod[1]<tabCurrentDate[1]) ||
      (tabBeginDatePeriod[0]===tabCurrentDate[2] && tabBeginDatePeriod[1]===tabCurrentDate[1] && tabBeginDatePeriod[2]<tabCurrentDate[0])){
      this.beginDatePeriodVerif = false;
      this.msgBeginDatePeriod = "Cette date est dépassé.";
      return false;
    }

    this.beginDatePeriodVerif = true;
    this.msgBeginDatePeriod = "";
    return true;
  }

  verifyEndDatePeriod(){
    if(this.period.endDatePeriod === ''){
      this.endDatePeriodVerif = false;
      this.msgEndDatePeriod = "Vous devez indiquer une date de fin de période."
      return false;
    }
    let tabBeginDatePeriod = this.period.beginDatePeriod.split('-');
    let tabEndDatePeriod = this.period.endDatePeriod.split('-');
    if(tabBeginDatePeriod.length ==3)
    {
      if(tabEndDatePeriod[0]<tabBeginDatePeriod[0] || tabEndDatePeriod[1]<tabBeginDatePeriod[1] ||
        (tabEndDatePeriod[0]===tabBeginDatePeriod[0] && tabEndDatePeriod[1]===tabBeginDatePeriod[1] && tabEndDatePeriod[2]<tabBeginDatePeriod[2])){
        this.endDatePeriodVerif = false;
        this.msgEndDatePeriod = "Vous avez indiquer une date de fin période qui précéde le début.";
        return false;
      }

      var dateBegin = new Date();
      dateBegin.setDate(Number(tabBeginDatePeriod[2]));
      dateBegin.setMonth(Number(tabBeginDatePeriod[1]));
      dateBegin.setFullYear(Number(tabBeginDatePeriod[0]));
      dateBegin.setHours(0);
      dateBegin.setMinutes(0);

      var dateEnd = new Date();
      dateEnd.setDate(Number(tabEndDatePeriod[2]));
      dateEnd.setMonth(Number(tabEndDatePeriod[1]));
      dateEnd.setFullYear(Number(tabEndDatePeriod[0]));
      dateEnd.setHours(0);
      dateEnd.setMinutes(0);
      var time = dateEnd.getTime() - dateBegin.getTime();
      var days = time / (1000 * 3600 * 24);

      if(days<3){
        this.endDatePeriodVerif = false;
        this.msgEndDatePeriod = "Votre période est trop courte (elle doit comporter au moins 3 jours)."
        return false;
      }
    }
    this.endDatePeriodVerif = true;
    this.msgEndDatePeriod = "";
    return true;
  }

  gotoPeriodList(){
    this.router.navigate(['/period']);
  }

}
