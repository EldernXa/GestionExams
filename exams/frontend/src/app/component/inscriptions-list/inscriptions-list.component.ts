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

  constructor(private route: ActivatedRoute, private inscriptionsService: InscriptionsService, private loginService:LoginService) {
    this.loginService.redirectIfNotLogin();
    this.inscriptions = [];
    this.inscriptionsService.findAll().subscribe(data=>{
      //this.inscriptions = data;
      this.inscriptions = this.inscriptions.sort((a,b) => b.year - a.year);
      for(var inscription of data){
        inscription.ue.durationExam = Number(inscription.ue.durationExam)/60;
        this.inscriptions.push(inscription);
      }
    });
  }

  ngOnInit(): void {
    this.inscriptionsService.findAll().subscribe(data=>{
      //this.inscriptions = data;
      this.inscriptions = this.inscriptions.sort((a,b) => b.year - a.year);
      for(var inscription of data){
        console.log(inscription.ue.durationExam);
        console.log(Number(inscription.ue.durationExam)/60);
        inscription.ue.durationExam = Number(inscription.ue.durationExam)/60;
        console.log(inscription.ue.durationExam);
        this.inscriptions.push(inscription);
      }
    });
  }

}
