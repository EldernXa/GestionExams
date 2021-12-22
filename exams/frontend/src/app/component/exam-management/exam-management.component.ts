import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Exam } from 'src/app/model/exam';
import { ExamService } from 'src/app/service/exam.service';

@Component({
  selector: 'app-exam-management',
  templateUrl: './exam-management.component.html',
  styleUrls: ['./exam-management.component.css']
})
export class ExamManagementComponent implements OnInit {

  id: number = -1;
  listExam: Exam[] = [];
  exam: Exam = new Exam();
  listUE: string[]= [];

  constructor(private route:ActivatedRoute, private examService:ExamService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.examService.findAllExamFromPeriod(this.id).subscribe(data => {
      this.listExam = data;
    });

    this.examService.findAllUeNameForCreatingExam(this.id).subscribe(data=>{
      this.listUE = data;
    });

  }

  onSubmit(){
    
  }

}
