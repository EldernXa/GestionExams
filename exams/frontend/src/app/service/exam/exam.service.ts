import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exam } from '../../model/exam/exam';

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
}
