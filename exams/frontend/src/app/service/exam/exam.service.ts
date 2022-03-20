import { HttpClient } from '@angular/common/http';
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
    return this.http.get<Exam[]>(this.usersUrl + "list/"+id);
  }

  public findAllUeNameForCreatingExam(id:number):Observable<string[]>{
    return this.http.get<string[]>(this.usersUrl+"listUE/"+id);
  }

  public save(exam: Exam){
    console.log(exam.year)
    return this.http.post<Exam>(this.usersUrl + "add", exam);
  }

  public hasStudent(idExam: number): Observable<boolean>{
    return this.http.get<boolean>(this.usersUrl + idExam +"/student");
  }

  public updatePlanning(idPeriod: number){
    return this.http.put("http://localhost:8080/period/" + idPeriod,idPeriod);
  }

  public getNextSessionForAnExam(nameUE: string, idPeriod: number):Observable<number>{
    return this.http.get<number>(this.usersUrl + "session/" + nameUE+"/"+idPeriod);
  }

  public isExamFinished(idExam: number, idPeriod: number): Observable<boolean>{
    return this.http.get<boolean>(this.usersUrl + "isFinish/" + idExam + "/" + idPeriod);
  }

  public isPeriodCanBeUndone(idPeriod: number): Observable<boolean>{
    return this.http.get<boolean>("http://localhost:8080/period/undoPlanify/"+idPeriod);
  }

  public initPeriod(idPeriod: number){
    return this.http.put("http://localhost:8080/period/initPeriod/" + idPeriod, idPeriod);
  }

  public isPeriodFinish(idPeriod: number):Observable<boolean>{
    return this.http.get<boolean>("http://localhost:8080/period/isFinished/"+idPeriod);
  }
}
