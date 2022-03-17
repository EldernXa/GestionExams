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

  subscribeable_ues : Ue[] = [];
  all_ues : Ue[] = [];
  inscriptions : Inscription[] = [];
  inscriptions_ue_name : string[] = [];
  current_year: number = new Date().getFullYear();
  modalRef: BsModalRef = new BsModalRef();

  constructor(private ueSubscribeableService: UeSubscribeableService, private modalService: BsModalService, private loginService: LoginService) {
    this.loginService.redirectIfNotLogin();
  }

  ngOnInit(){

    this.subscribeable_ues = [];

    this.ueSubscribeableService.findAllSubscribeableUes(this.current_year).subscribe( data => {
      this.subscribeable_ues = data;
    })
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  subscribe(ue: Ue){
    // if(confirm("S'inscrire à une Ue implique un engagement de votre part à l'assiduité et à la non possibilité de se rétracter. Confirmez-vous que vous souhaitez vous inscrire à l'Ue "+ue.name+" pour l'année "+this.current_year+ " ?"))
    this.ueSubscribeableService.subscribeToUe(this.current_year,ue).subscribe( data => {
      this.modalRef.hide();
      this.ngOnInit();
    });
  }

  decline(){
    this.modalRef.hide();
  }

}
