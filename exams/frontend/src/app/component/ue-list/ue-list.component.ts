import { Component, OnInit } from '@angular/core';
import {Ue} from "../../model/ue/ue";
import {UeService} from "../../service/ue/ue-service.service";

@Component({
  selector: 'app-ue-list',
  templateUrl: './ue-list.component.html',
  styleUrls: ['./ue-list.component.css']
})
export class UeListComponent implements OnInit {

  ues : Ue[];

  constructor(private ueService: UeService) {
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
