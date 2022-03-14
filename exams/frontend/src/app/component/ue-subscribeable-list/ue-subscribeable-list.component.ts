import { Component, OnInit } from '@angular/core';
import {Ue} from "../../model/ue/ue";
import {UeService} from "../../service/ue/ue-service.service";
import {LoginService} from "../../service/login/login.service";
import {UeSubscribeableService} from "../../service/ue-subscribeable/ue-subscribeable-service.service";
import {Inscription} from "../../model/inscription/inscription";
import {waitForAsync} from "@angular/core/testing";

@Component({
  selector: 'app-ue-subscribeable-list',
  templateUrl: './ue-subscribeable-list.component.html',
  styleUrls: ['./ue-subscribeable-list.component.css']
})
export class UeSubscribeableListComponent implements OnInit {

  subscribeable_ues : Ue[];
  all_ues : Ue[];
  inscriptions : Inscription[];
  inscriptions_ue_name : string[]
  current_year: number;

  constructor(private ueSubscribeableService: UeSubscribeableService, private loginService: LoginService) {
    this.loginService.redirectIfNotLogin();
    this.subscribeable_ues = [];
    this.all_ues = [];
    this.inscriptions = [];
    this.inscriptions_ue_name = [];
    this.current_year = new Date().getFullYear();
    this.init();
  }

  ngOnInit(){
    this.init();
  }

  subscribe(ue: Ue){
    this.ueSubscribeableService.subscribeToUe(this.current_year,ue).subscribe();
    this.ngOnInit();
  }

  init(){
    this.subscribeable_ues = [];
    this.all_ues = [];
    this.inscriptions = [];
    this.inscriptions_ue_name = [];

    this.ueSubscribeableService.findAllUe().subscribe(data=>{
      this.all_ues = data;

      this.ueSubscribeableService.findAllInscriptions().subscribe(data=>{
        this.inscriptions = data;

        this.inscriptions.forEach((inscription) => {
          // get inscription of this year
          if(inscription.year == this.current_year)
            this.inscriptions_ue_name.push(inscription.ue.name);
        });
        this.all_ues.forEach((ue) => {
          if(!this.inscriptions_ue_name.includes(ue.name))
            this.subscribeable_ues.push(ue);
        });
      });
    });
  }

}
