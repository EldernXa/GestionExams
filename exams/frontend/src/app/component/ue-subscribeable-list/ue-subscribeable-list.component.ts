import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
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
  modalRef: BsModalRef;

  constructor(private ueSubscribeableService: UeSubscribeableService, private modalService: BsModalService, private loginService: LoginService) {
    console.log("constructor");
    this.loginService.redirectIfNotLogin();
    this.subscribeable_ues = [];
    this.all_ues = [];
    this.inscriptions = [];
    this.inscriptions_ue_name = [];
    this.current_year = new Date().getFullYear();
    this.modalRef = new BsModalRef();

  }

  ngOnInit(){

    console.log("ngOnInit");

    this.subscribeable_ues = [];
    this.all_ues = [];
    this.inscriptions = [];
    this.inscriptions_ue_name = [];

    this.ueSubscribeableService.findAllUe().subscribe(data=>{
      this.all_ues = data;
      console.log("all ue : "+ this.all_ues.length);
      this.ueSubscribeableService.findAllInscriptions().subscribe(data=>{
        this.inscriptions = data;
        console.log("all inscriptions : "+this.inscriptions.length);
        this.inscriptions.forEach((inscription) => {
          // get inscription of this year
          if(inscription.year == this.current_year){
            console.log("inscription year ="+inscription.year + " = " + this.current_year);
            this.inscriptions_ue_name.push(inscription.ue.name);
          }
        });
        console.log("inscriptions name : "+this.inscriptions_ue_name.length)
        this.all_ues.forEach((ue) => {
          console.log("entering in all ues for loop");
          if(!this.inscriptions_ue_name.includes(ue.name))
            this.subscribeable_ues.push(ue);
        });
        console.log("subscribeable ue : "+this.subscribeable_ues.length);
      });
    });
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  subscribe(ue: Ue){
    // if(confirm("S'inscrire à une Ue implique un engagement à l'assiduité. Confirmez-vous que vous souhaitez vous inscrire à l'Ue "+ue.name+" pour l'année "+this.current_year+ " ?"))
    this.ueSubscribeableService.subscribeToUe(this.current_year,ue).subscribe();
    this.modalRef.hide();
    this.ngOnInit();
  }

  decline(){
    this.modalRef.hide();
  }

}
