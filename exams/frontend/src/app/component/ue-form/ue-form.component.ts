import { Component } from '@angular/core';
import {Period} from "../../model/period/period";
import {ActivatedRoute, Router} from "@angular/router";
import {Ue} from "../../model/ue/ue";
import {UeService} from "../../service/ue/ue-service.service";

@Component({
  selector: 'app-ue-form',
  templateUrl: './ue-form.component.html',
  styleUrls: ['./ue-form.component.css']
})
export class UeFormComponent {

  ue: Ue;

  constructor(private route: ActivatedRoute, private router: Router, private ueService: UeService){
    this.ue = new Ue();
  }

  onSubmit(){
    this.ueService.addUe(this.ue).subscribe(result=>{
      this.gotoPeriodList();
    });
  }

  gotoPeriodList(){
    this.router.navigate(['/ue']);
  }

}
