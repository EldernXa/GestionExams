import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { LoginService } from '../login/login.service';
import {Grade} from "../../model/grade/grade";

@Injectable()
export class GradeService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:11003/grades/';
  }

  public findAll(idExam: number) : Observable<Grade[]>{
    return this.http.get<Grade[]>(this.usersUrl + "exam/"+idExam, this.loginService.getHeaders());
  }

  public saveAllGrades(idExam: number, grades : Grade[]){
    return this.http.post<void>(this.usersUrl+"exams/"+idExam, grades, this.loginService.getHeaders());
  }

  public saveGrade(idExam: number, grade: Grade){
    return this.http.post<void>(this.usersUrl+"exam/"+idExam,grade, this.loginService.getHeaders());
  }

  public getHttp() :HttpClient {
    return this.http;
  }

}
