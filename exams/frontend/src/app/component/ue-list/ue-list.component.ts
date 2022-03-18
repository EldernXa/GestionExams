import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login/login.service';
import {Ue} from "../../model/ue/ue";
import {UeService} from "../../service/ue/ue-service.service";

@Component({
  selector: 'app-ue-list',
  templateUrl: './ue-list.component.html',
  styleUrls: ['./ue-list.component.css']
})
export class UeListComponent implements OnInit {

  ues : Ue[];
  hoursAndMin: string[] = [];

  constructor(private ueService: UeService, private loginService: LoginService) {
    this.loginService.redirectIfNotLogin();
    this.ues = [];
  }

  ngOnInit(){
    this.ueService.findAll().subscribe(data=>{
      this.ues = data;
      for(let i=0; i<this.ues.length; i++)
        this.hoursAndMin[i]=this.minToHoursAndMin(this.ues[i].durationExam);
    });
  }

  delete(index: number){
    this.ueService.deleteUe(this.ues[index].name).subscribe();
    this.ueService.findAll().subscribe(data=>{
      this.ues = data;
    });
  }

  public minToHoursAndMin(minutes: number): string{
    let h = Math.floor(minutes/60);
    let hours = h.toString();
    let m = minutes%60;
    let min = (m<10) ? ("0"+m.toString()) : (m.toString());
    let hoursAndMin = hours + "h" + min;
    console.log(hoursAndMin);
    return hoursAndMin;
  }

}
