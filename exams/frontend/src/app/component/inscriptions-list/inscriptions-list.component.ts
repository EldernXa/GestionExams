import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LoginService} from "../../service/login/login.service";
import {Inscription} from "../../model/inscription/inscription";
import {InscriptionsService} from "../../service/inscriptions/inscriptions-service.service";

@Component({
  selector: 'app-inscriptions-list',
  templateUrl: './inscriptions-list.component.html',
  styleUrls: ['./inscriptions-list.component.css']
})
export class InscriptionsListComponent implements OnInit {

  inscriptions: Inscription[];
  hoursAndMin: string[] = [];

  constructor(private route: ActivatedRoute, private inscriptionsService: InscriptionsService, private loginService:LoginService) {
    this.loginService.redirectIfNotLogin();
    this.inscriptions = [];
  }

  ngOnInit(): void {
    this.inscriptionsService.findAll().subscribe(data=>{
      for(var inscription of data)
        this.inscriptions.push(inscription);
      this.inscriptions.sort((a,b) => b.year - a.year);
      for(let i=0; i<this.inscriptions.length; i++)
        this.hoursAndMin[i] = this.minToHoursAndMin(this.inscriptions[i].ue.durationExam);
    });
  }

  public minToHoursAndMin(minutes: number): string{
    let h = Math.floor(minutes/60);
    let hours = h.toString();
    let m = minutes%60;
    let min = (m<10) ? ("0"+m.toString()) : (m.toString());
    return hours + "h" + min;
  }
}
