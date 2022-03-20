import { Component, OnInit } from '@angular/core';
import { Exam } from 'src/app/model/exam/exam';
import { ExamViewService } from 'src/app/service/exam-view/exam-view.service';
import { LoginService } from 'src/app/service/login/login.service';

@Component({
  selector: 'app-exams-view',
  templateUrl: './exams-view.component.html',
  styleUrls: ['./exams-view.component.css']
})
export class ExamsViewComponent implements OnInit {

  listExam: Exam[] = [];

  constructor(private examViewService: ExamViewService, private loginService: LoginService) {
    this.loginService.redirectIfNotLogin();
  }

  ngOnInit(): void {
    this.examViewService.findAllNextExamForAStudent().subscribe(data=>{
      this.listExam = data;
    });
  }

  getToolTipText(exam: Exam):string {
    if(exam.session == 2)
      return "Uniquement pour ceux ayant échoué en session 1 !"
    return "";
  }

}
