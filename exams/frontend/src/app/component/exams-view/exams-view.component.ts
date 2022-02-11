import { Component, OnInit } from '@angular/core';
import { Exam } from 'src/app/model/exam/exam';
import { ExamViewService } from 'src/app/service/exam-view/exam-view.service';

@Component({
  selector: 'app-exams-view',
  templateUrl: './exams-view.component.html',
  styleUrls: ['./exams-view.component.css']
})
export class ExamsViewComponent implements OnInit {

  listExam: Exam[] = [];

  constructor(private examViewService: ExamViewService) { }

  ngOnInit(): void {
    this.examViewService.findAllNextExamForAStudent().subscribe(data=>{
      this.listExam = data;
    });

  }

}
