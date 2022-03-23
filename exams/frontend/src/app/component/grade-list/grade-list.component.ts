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
    });
  }


  onSubmit(){
    this.gradeService.saveAllGrades(this.idExam,this.grades).subscribe();
  }

  saveAll(){
    this.gradeService.saveAllGrades(this.idExam,this.grades).subscribe();
    window.history.back();
  }

  checkValidity(){
    let bool = true;
    for(let g of this.grades){
      if((g.value < 0 || g.value > 20 )|| g.value == null)
        bool = false;
    }
    this.isValid = bool;
  }

}
