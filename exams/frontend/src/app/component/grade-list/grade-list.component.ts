import { Component, OnInit} from '@angular/core';
import {GradeService} from "../../service/grade/grade-service.service";
import {Student} from "../../model/student/student";
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
  students: Student[];
  grades: Grade[];

  constructor(private route: ActivatedRoute, private gradeService: GradeService, private loginService:LoginService) {
    this.loginService.redirectIfNotLogin();
    this.idExam = Number(this.route.snapshot.paramMap.get('id'));
    this.students = [];
    this.grades = [];
    this.gradeService.findAll(this.idExam).subscribe(data=>{
      //this.students = data;
      this.grades = data;
    });
  }

  ngOnInit(){
    this.gradeService.findAll(this.idExam).subscribe(data=>{
      //this.students = data;
      this.grades = data;
      console.log(this.grades.length)
      //this.students.forEach(value => {if(value.grades[0].value == -1) value.grades[0].value = 0});
    });
  }

  onSubmit(){
    this.gradeService.saveAllGrades(this.idExam,this.students);
  }

  updateGrade(index : number){
    //this.gradeService.saveGrade(this.idExam,this.students[index]).subscribe(res => console.log(res),error => console.log(error));
    this.gradeService.saveGrade(this.idExam, this.grades[index]).subscribe(res => console.log(res),error => console.log(error));
  }

}
