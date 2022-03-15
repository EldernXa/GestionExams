import { Component, OnInit } from '@angular/core';
import {Inscription} from "../../model/inscription/inscription";
import {ActivatedRoute} from "@angular/router";
import {InscriptionsService} from "../../service/inscriptions/inscriptions-service.service";
import {LoginService} from "../../service/login/login.service";
import {Grade} from "../../model/grade/grade";
import {GradesViewService} from "../../service/grades-view/grades-view-service.service";

@Component({
  selector: 'app-grades-view',
  templateUrl: './grades-view.component.html',
  styleUrls: ['./grades-view.component.css']
})
export class GradesViewComponent implements OnInit {

  idStudent: number = -1;
  grades: Grade[] = [];

  constructor(private route: ActivatedRoute, private gradeViewService: GradesViewService, private loginService:LoginService) {
    this.loginService.redirectIfNotLogin();
    this.idStudent = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit(): void {
    this.gradeViewService.findAll(this.idStudent).subscribe(data=>{
      this.grades = data;
      //this.grades = this.grades.sort((a,b) => b.exam.year - a.exam.year);
      console.log(this.grades.length)
    });
  }

}
