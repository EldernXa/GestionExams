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

  constructor(private ueService: UeService, private loginService: LoginService) {
    this.loginService.redirectIfNotLogin();
    this.ues = [];
    this.ueService.findAll().subscribe(data=>{
      this.ues = data;
    });
  }

  ngOnInit(){
    this.ueService.findAll().subscribe(data=>{
      this.ues = data;
    });
  }

  delete(index: number){
    this.ueService.deleteUe(this.ues[index].name).subscribe();
    this.ueService.findAll().subscribe(data=>{
      this.ues = data;
    });
  }

}
