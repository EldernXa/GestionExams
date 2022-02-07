import { Component, OnInit } from '@angular/core';
import {FormControl} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UeService} from "../../service/ue/ue-service.service";
import {Ue} from "../../model/ue/ue";

@Component({
  selector: 'app-ue-management',
  templateUrl: './ue-management.component.html',
  styleUrls: ['./ue-management.component.css']
})
export class UeManagementComponent implements OnInit {

  name: string = "";
  ue: Ue = new Ue();
  selectControl:FormControl = new FormControl();

  constructor(private route:ActivatedRoute, private ueService:UeService, private router: Router) { }

  ngOnInit(): void {
    this.name = String(this.route.snapshot.paramMap.get('id'));
    this.ueService.getUe(this.name).subscribe(data => {
      this.ue = data;
    });
  }

  onSubmit(){
    this.ue.name = this.name;
    this.ueService.updateUe(this.ue.name,this.ue).subscribe(data => {
      this.redirectTo('/ue');
    });
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
      this.router.navigate([uri]));
  }

}
