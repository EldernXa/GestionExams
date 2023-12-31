import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {Ue} from "../../model/ue/ue";
import {UeService} from "../../service/ue/ue-service.service";
import { LoginService } from 'src/app/service/login/login.service';

@Component({
  selector: 'app-ue-form',
  templateUrl: './ue-form.component.html',
  styleUrls: ['./ue-form.component.css']
})
export class UeFormComponent {

  ue: Ue;
  isNameGood = true;

  constructor(private router: Router, private ueService: UeService, private loginService: LoginService){
    this.loginService.redirectIfNotLogin();
    this.ue = new Ue();
  }

  onSubmit(){
    this.ueService.isUeNameGood(this.ue.name).subscribe(
      (isUeNameGood)=>{
        if(isUeNameGood){
          this.isNameGood = true;
          this.ueService.addUe(this.ue).subscribe(result=>{
            this.gotoPeriodList();
          });
        }
        else{
          this.isNameGood = false;
        }
      }
    );
    
  }

  gotoPeriodList(){
    this.router.navigate(['/ue']);
  }

}
