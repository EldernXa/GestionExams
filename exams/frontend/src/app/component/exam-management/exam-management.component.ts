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
    this.examService.findAllExamFromPeriod(this.id).subscribe(data => {
      this.listExam = data;
    });

    this.examService.findAllUeNameForCreatingExam(this.id).subscribe(data=>{
      this.listUE = data;
    });

  }

  onSubmit(){
    this.exam.idPeriod = this.id;
    console.log(this.exam.ue);
    console.log(this.exam.year);
    console.log(this.exam.idPeriod);
    this.examService.save(this.exam).subscribe(result => {
      this.redirectTo('/periodManagement/'+this.id);
    });
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
 }

}
