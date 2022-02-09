import { Component, OnInit} from '@angular/core';
import {GradeService} from "../../service/grade/grade-service.service";
import {Student} from "../../model/student/student";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-grade-list',
  templateUrl: './grade-list.component.html',
  styleUrls: ['./grade-list.component.css']
})
export class GradeListComponent implements OnInit {

  idExam : number = -1;
  students: Student[];

  constructor(private route: ActivatedRoute, private gradeService: GradeService) {
    this.idExam = Number(this.route.snapshot.paramMap.get('id'));
    this.students = [];
    this.gradeService.findAll(this.idExam).subscribe(data=>{
      this.students = data;
    });
  }

  ngOnInit(){
    this.gradeService.findAll(this.idExam).subscribe(data=>{
      this.students = data;
      this.students.forEach(value => {if(value.grade == -1)value.grade = undefined});
    });
  }

  onSubmit(){
    this.gradeService.saveAllGrades(this.idExam,this.students);
  }

  updateGrade(index : number){
    this.gradeService.saveGrade(this.idExam,this.students[index]).subscribe(res => console.log(res),error => console.log(error));
  }

}
