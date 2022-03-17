import { Component, OnInit} from '@angular/core';
import {GradeService} from "../../service/grade/grade-service.service";
import {ActivatedRoute} from "@angular/router";
import { LoginService } from 'src/app/service/login/login.service';
import {Grade} from "../../model/grade/grade";

@Component({
  selector: 'app-grade-list',
  templateUrl: './grade-list.component.html',
  styleUrls: ['./grade-list.component.css']
})
export class GradeListComponent implements OnInit {

  idExam : number = -1;
  grades: Grade[];
  isValid: boolean = true;

  constructor(private route: ActivatedRoute, private gradeService: GradeService, private loginService:LoginService) {
    this.loginService.redirectIfNotLogin();
    this.idExam = Number(this.route.snapshot.paramMap.get('id'));
    this.grades = [];
  }

  ngOnInit(){
    this.gradeService.findAll(this.idExam).subscribe(data=>{
      this.grades = data;
      console.log(this.grades)
    });
  }


  onSubmit(){
    console.log(this.grades);
    this.gradeService.saveAllGrades(this.idExam,this.grades).subscribe();
  }

  updateGrade(index : number){
    this.gradeService.saveGrade(this.idExam, this.grades[index]).subscribe(res => console.log(res),error => console.log(error));
  }

  saveAll(){
    this.gradeService.saveAllGrades(this.idExam,this.grades).subscribe();
  }

  checkValidity(){
    let bool = true;
    for(let i=0; i<this.grades.length; i++){
      if((this.grades[i].value < 0 || this.grades[i].value > 20 )|| this.grades[i].value == null)
        bool = false;
    }
    this.isValid = bool;
  }


}
