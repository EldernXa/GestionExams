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
      this.inscriptions = data;
    });
  }

  ngOnInit(): void {
    this.inscriptionsService.findAll().subscribe(data=>{
      this.inscriptions = data;
    });
  }

}
