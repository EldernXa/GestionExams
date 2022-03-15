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

  grades: Grade[] = [];

  constructor(private route: ActivatedRoute, private gradeViewService: GradesViewService, private loginService:LoginService) {
    this.loginService.redirectIfNotLogin();
  }

  ngOnInit(): void {
    this.gradeViewService.findAll().subscribe(data=>{
      this.grades = data;
      this.grades = this.grades.sort((a,b) => b.year - a.year);
      console.log(this.grades.length);
      console.log("student : "+this.grades[0].lastName);
      console.log("exam : "+this.grades[0].idExam);

    });
  }

}
