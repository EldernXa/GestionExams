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
      for(let i=0; i<this.listExam.length; i++){
        this.examViewService.getNewBeginDate(this.listExam[i].idExam).subscribe(
          data2=>{
            this.listExam[i].beginDateExam = data2;
          }
        );


        this.examViewService.getNewEndDate(this.listExam[i].idExam).subscribe(
          data2=>{
            this.listExam[i].endDateExam = data2;
          }
        );

      }
    });

  }

}
