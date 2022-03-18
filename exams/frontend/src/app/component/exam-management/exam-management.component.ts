import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Exam } from 'src/app/model/exam/exam';
import { ExamService } from 'src/app/service/exam/exam.service';
import { PeriodService } from 'src/app/service/period/period-service.service';

@Component({
  selector: 'app-exam-management',
  templateUrl: './exam-management.component.html',
  styleUrls: ['./exam-management.component.css']
})
export class ExamManagementComponent implements OnInit {

  id: number = -1;
  listExam: Exam[] = [];
  listHasStudent: boolean[] = [];
  exam: Exam = new Exam();
  listUE: string[]= [];
  selectControl:FormControl = new FormControl();
  ueNameVerif = true;
  sessionVerif = false;
  yearVerif = true;
  msgNameUe = "";
  msgSession = "";
  msgYear = "";
  isDisable = true;

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
      for(let i=0 ; i<this.listExam.length; i++){
         this.examService.hasStudent(this.listExam[i].idExam).subscribe( hasStudent => {
           this.listHasStudent[i] = hasStudent;
           console.log(hasStudent)
        });
        this.examService.isExamFinished(this.listExam[i].idExam, this.id).subscribe((isExamFinished)=>{
          this.listExam[i].isFinish = isExamFinished;
        });
      }
    });

    this.examService.findAllUeNameForCreatingExam(this.id).subscribe(data=>{
      this.listUE = data;
    });

    this.verifyPlanify();

  }

  onChange(newValue:string){
    this.examService.getNextSessionForAnExam(newValue, this.id).subscribe((data)=>{
      this.exam.session = data;
      this.sessionVerif = true;
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

 verifyPlanify(){
   this.examService.isPeriodCanBeUndone(this.id).subscribe((data)=>{
     this.isDisable = data;
   });
 }

 initPeriod(){
   this.examService.initPeriod(this.id).subscribe(()=>{
     this.update();
   });
 }

}
