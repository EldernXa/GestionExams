import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exam } from '../model/exam';

@Injectable()
export class ExamService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/exam/';
  }

  public findAllExamFromPeriod(id:number):Observable<Exam[]>{
    return this.http.get<Exam[]>(this.usersUrl + "list/"+id);
  }

  public findAllUeNameForCreatingExam(id:number):Observable<string[]>{
    return this.http.get<string[]>(this.usersUrl+"listUE/"+id);
  }

  public save(exam: Exam){
    return this.http.post<Exam>(this.usersUrl + "add", exam);
  }

  public getNewBeginDate(idExam: number){
    return this.http.get(this.usersUrl + idExam + "/beginDate", {responseType: 'text'});
  }

  public getNewEndDate(idExam: number){
    return this.http.get(this.usersUrl + idExam + "/endDate", {responseType: 'text'});
  }

  public updatePlanning(idPeriod: number){
    return this.http.put("http://localhost:8080/period/" + idPeriod,idPeriod);
  }
}
