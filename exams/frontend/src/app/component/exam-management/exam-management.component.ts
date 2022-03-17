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
  ueNameVerif = true;
  sessionVerif = true;
  yearVerif = true;
  msgNameUe = "";
  msgSession = "";
  msgYear = "";

  constructor(private route:ActivatedRoute, private examService:ExamService,
              private router: Router) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.update();
    this.exam.session = 1;
  }

  update(){
    this.examService.findAllExamFromPeriod(this.id).subscribe(data => {
      this.listExam = data;
    });

    this.examService.findAllUeNameForCreatingExam(this.id).subscribe(data=>{
      this.listUE = data;
    });

  }

  onChange(newValue:string){
    this.examService.getNextSessionForAnExam(newValue, this.id).subscribe((data)=>{
      console.log(data);
    });
  }

  onSubmit(){

    if(this.verifyNameUE()){
      this.exam.idPeriod = this.id;
      this.exam.beginDateExam = "";
      this.exam.endDateExam = "";
      this.exam.nameRoom = "";
      this.exam.year = new Date().getFullYear();
      this.examService.save(this.exam).subscribe(result => {
        this.redirectTo('/period/'+this.id);
      });
    }
  }

  verifyNameUE(){
    if(this.exam.ue.name === ''){
      this.ueNameVerif = false;
      this.msgNameUe = "Veillez choisir une UE parmi ceux proposés.";
      return false;
    }

    this.ueNameVerif = true;
    this.msgNameUe = "";
    return true;
  }

  onPlan(){
    this.examService.updatePlanning(this.id).subscribe(result=>{this.update();});
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
 }

}
