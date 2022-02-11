import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exam } from 'src/app/model/exam/exam';
import { LoginService } from '../login/login.service';

@Injectable()
export class ExamViewService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/';
  }

  public findAllNextExamForAStudent():Observable<Exam[]>{
    return this.http.get<Exam[]>(this.usersUrl + "student/exams", this.loginService.getHeaders());
  }

  public getNewBeginDate(idExam: number){
    return this.http.get(this.usersUrl+"exam/" + idExam + "/beginDate", {headers: this.loginService.getHeaders().headers, responseType: 'text'});
  }

  public getNewEndDate(idExam: number){
    return this.http.get(this.usersUrl+"exam/" + idExam + "/endDate", {headers: this.loginService.getHeaders().headers, responseType: 'text'});
  }

  public getNewBeginHour(idExam: number){
    return this.http.get(this.usersUrl+"exam/" + idExam + "/beginHour", {headers: this.loginService.getHeaders().headers, responseType: 'text'});
  }

  public getNewEndHour(idExam: number){
    return this.http.get(this.usersUrl + "exam/" + idExam + "/endHour", {headers: this.loginService.getHeaders().headers, responseType: 'text'});
  }

}
