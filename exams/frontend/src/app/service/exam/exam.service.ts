import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exam } from '../../model/exam/exam';
import { LoginService } from '../login/login.service';

@Injectable()
export class ExamService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/exam/';
  }

  public findAllExamFromPeriod(id:number):Observable<Exam[]>{
    return this.http.get<Exam[]>(this.usersUrl + "list/"+id, this.loginService.getHeaders());
  }

  public findAllUeNameForCreatingExam(id:number):Observable<string[]>{
    return this.http.get<string[]>(this.usersUrl+"listUE/"+id, this.loginService.getHeaders());
  }

  public save(exam: Exam){
    return this.http.post<Exam>(this.usersUrl + "add", exam, this.loginService.getHeaders());
  }

  public getNewBeginDate(idExam: number){
    return this.http.get(this.usersUrl + idExam + "/beginDate", {headers: this.loginService.getHeaders().headers, responseType: 'text'});
  }

  public getNewEndDate(idExam: number){
    return this.http.get(this.usersUrl + idExam + "/endDate", {headers: this.loginService.getHeaders().headers, responseType: 'text'});
  }

  public updatePlanning(idPeriod: number){
    return this.http.put("http://localhost:8080/period/" + idPeriod,idPeriod, this.loginService.getHeaders());
  }
}
