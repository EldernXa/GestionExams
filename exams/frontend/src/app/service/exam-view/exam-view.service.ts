import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exam } from 'src/app/model/exam/exam';

@Injectable()
export class ExamViewService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/';
  }

  public findAllNextExamForAStudent():Observable<Exam[]>{
    return this.http.get<Exam[]>(this.usersUrl + "student/exams");
  }

  public getNewBeginDate(idExam: number){
    return this.http.get(this.usersUrl+"exam/" + idExam + "/fullBeginDate", {responseType: 'text'});
  }

  public getNewEndDate(idExam: number){
    return this.http.get(this.usersUrl + "exam/" + idExam + "/fullEndDate", {responseType: 'text'});
  }

}
