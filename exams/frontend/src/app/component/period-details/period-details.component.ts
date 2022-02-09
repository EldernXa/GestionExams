import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Period } from 'src/app/model/period/period';
import { PeriodService } from 'src/app/service/period/period-service.service';
import {ExamService} from "../../service/exam/exam.service";
import {Exam} from "../../model/exam/exam";
import {Observable} from "rxjs";
import { LoginService } from 'src/app/service/login/login.service';

@Component({
  selector: 'app-period-details',
  templateUrl: './period-details.component.html',
  styleUrls: ['./period-details.component.css']
})
export class PeriodDetailsComponent implements OnInit {
  id: number = -1;
  period: Period = new Period();
  exams: Exam[] = [];

  constructor(private route: ActivatedRoute, private periodService: PeriodService, private examService: ExamService, 
    private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.redirectIfLogin();
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.examService.findAllExamFromPeriod(this.id).subscribe(data =>{
      this.exams = data;
    });
    this.periodService.getPeriod(this.id).subscribe(data =>{
      this.period = data;
      this.periodService.getNewBeginPeriod(this.period.id)
              .subscribe(data=>{
                this.period.beginDatePeriod = data;
              });
      this.periodService.getNewEndPeriod(this.period.id)
              .subscribe(data =>{
                this.period.endDatePeriod = data;
              });
    });
  }

}
