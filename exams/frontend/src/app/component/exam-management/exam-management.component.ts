import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Exam } from 'src/app/model/exam/exam';
import { ExamService } from 'src/app/service/exam/exam.service';

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
  selectControl:FormControl = new FormControl();

  constructor(private route:ActivatedRoute, private examService:ExamService,
              private router: Router) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.update();
  }

  update(){
    this.examService.findAllExamFromPeriod(this.id).subscribe(data => {
      this.listExam = data;
      for(let i=0; i<this.listExam.length; i++){
        this.examService.getNewBeginDate(this.listExam[i].idExam).subscribe(
          data2=>{
            this.listExam[i].beginDateExam = data2;
          }
        );

        this.examService.getNewBeginHour(this.listExam[i].idExam).subscribe(
          beginHour => {
            this.listExam[i].beginDateExam += "\n" + beginHour;
          }
        );

        this.examService.getNewEndDate(this.listExam[i].idExam).subscribe(
          data2=>{
            this.listExam[i].endDateExam = data2;
          }
        );

        this.examService.getNewEndHour(this.listExam[i].idExam).subscribe(
          endHour => {
            this.listExam[i].endDateExam += "\n" + endHour;
          }
        );
      }
    });

    this.examService.findAllUeNameForCreatingExam(this.id).subscribe(data=>{
      this.listUE = data;
    });

  }

  onSubmit(){
    this.exam.idPeriod = this.id;
    this.exam.beginDateExam = "";
    this.exam.endDateExam = "";
    this.exam.nameRoom = "";
    this.examService.save(this.exam).subscribe(result => {
      this.redirectTo('/period/'+this.id);
    });
  }

  onPlan(){
    this.examService.updatePlanning(this.id).subscribe(result=>{this.update();});
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
 }

}
