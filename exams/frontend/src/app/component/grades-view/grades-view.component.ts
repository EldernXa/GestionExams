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
  gradesS1: Grade[] = [];
  averages: number[] = [];
  current_points: number = 0;
  current_credits: number = 0;
  current_average: number = 0;
  hasS2G: boolean = false;
  valueS2G: number = 0;

  constructor(private route: ActivatedRoute, private gradeViewService: GradesViewService, private loginService:LoginService) {
    this.loginService.redirectIfNotLogin();
  }

  ngOnInit(): void {
    this.gradeViewService.findAll().subscribe(data=>{
      this.grades = data;
      this.grades = this.grades.sort((a,b) => b.year - a.year);
      for(const i in this.grades){
        if(this.grades[i].session == 1)
          this.gradesS1.push(this.grades[i]);
      }
      this.setAverage()
    });
  }

  setAverage(): void{
    let grades: Grade[] = [];
    let years: number[] = []
    this.grades.forEach((g) => {
      if(!years.includes(g.year))
        years.push(g.year);
    });
    years.forEach((y) => {
      grades = [];
      this.gradesS1.forEach((g) => {
        if (g.year == y)
          grades.push(g)
      });
      grades.forEach((g) => {
        this.addToAverage(g)
      });
      this.averageCalculation();
      this.averages[y]=Math.round(this.current_average*100)/100;
    });

  }

  addAverageVariables(points: number, credits: number): void{
    this.current_points += (points * credits);
    this.current_credits += credits;
  }

  addToAverage(grade: Grade): void{
      if(this.hasSession2Grade(grade)){
        let s2;
        s2 = this.getValueSession2Grade(grade);
        if(grade.value < s2)
          this.addAverageVariables(s2,grade.credit)
      }
      else
        this.addAverageVariables(grade.value,grade.credit)
  }

  averageCalculation(): number{
    this.current_average = this.current_points / this.current_credits;
    console.log(this.current_points + " / " + this.current_credits + " = " + this.current_average)
    this.current_points = 0;
    this.current_credits = 0;
    return this.current_average;
  }

  hasSession2Grade(grade : Grade): boolean{
      this.hasS2G = false;
      this.grades.forEach((g) => {
        if (grade.ue_name == g.ue_name && grade.year == g.year && g.session == 2)
          this.hasS2G = true;
      });
      return this.hasS2G;
  }

  getValueSession2Grade(grade : Grade): number{
    this.valueS2G = 0;
    this.grades.forEach((g) => {
      if(grade.ue_name == g.ue_name && grade.year == g.year && g.session == 2)
        this.valueS2G = g.value;
    });
    return this.valueS2G;
  }

  showMessage(message : string): void{
    console.log(message);
  }

  getToolTipText():string {
    return "Moyenne calculée à partir des UE notées de l'année !"
  }

}
