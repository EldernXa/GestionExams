import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Student } from '../../model/student/student';
import { map, Observable } from 'rxjs';
import { LoginService } from '../login/login.service';
import {Grade} from "../../model/grade/grade";

@Injectable()
export class GradeService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/grades/';
  }

  public findAll(idExam: number) : Observable<Grade[]>{
    let list = this.http.get<Grade[]>(this.usersUrl + "exam/"+idExam, this.loginService.getHeaders());
    return list;
  }

  public saveAllGrades(idExam: number, grades : Grade[]){
    console.log("save all grades");
    return this.http.post<void>(this.usersUrl+"exams/"+idExam, grades, this.loginService.getHeaders());
  }

  public saveGrade(idExam: number, grade: Grade){
    console.log("save grade of student  "+grade.idStudent + " for exam :"+grade.idExam);
    return this.http.post<void>(this.usersUrl+"exam/"+idExam,grade, this.loginService.getHeaders());
  }

  public getHttp() :HttpClient {
    return this.http;
  }

}
