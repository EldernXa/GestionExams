import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {Ue} from "../../model/ue/ue";
import {LoginService} from "../../service/login/login.service";
import {UeSubscribeableService} from "../../service/ue-subscribeable/ue-subscribeable-service.service";
import {Inscription} from "../../model/inscription/inscription";

@Component({
  selector: 'app-ue-subscribeable-list',
  templateUrl: './ue-subscribeable-list.component.html',
  styleUrls: ['./ue-subscribeable-list.component.css']
})
export class UeSubscribeableListComponent implements OnInit {

  subscribeable_ues : Ue[] = [];
  hoursAndMin: string[] = [];
  inscriptions : Inscription[] = [];
  current_year: number = new Date().getFullYear();
  modalRef: BsModalRef = new BsModalRef();

  constructor(private ueSubscribeableService: UeSubscribeableService, private modalService: BsModalService, private loginService: LoginService) {
    this.loginService.redirectIfNotLogin();
  }

  ngOnInit(){

    this.subscribeable_ues = [];
    this.ueSubscribeableService.findAllSubscribeableUes(this.current_year).subscribe( data => {
      this.subscribeable_ues = data;
      for(let i=0; i<this.subscribeable_ues.length; i++)
        this.hoursAndMin[i] = this.minToHoursAndMin(this.subscribeable_ues[i].durationExam);
    })
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  subscribe(ue: Ue){
    this.ueSubscribeableService.subscribeToUe(this.current_year,ue).subscribe( data => {
      this.modalRef.hide();
      this.ngOnInit();
    });
  }

  decline(){
    this.modalRef.hide();
  }

  public minToHoursAndMin(minutes: number): string{
    let h = Math.floor(minutes/60);
    let hours = h.toString();
    let m = minutes%60;
    let min = (m<10) ? ("0"+m.toString()) : (m.toString());
    return hours + "h" + min;
  }

}
