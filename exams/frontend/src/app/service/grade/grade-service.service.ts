import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Student } from '../../model/student/student';
import { map, Observable } from 'rxjs';
import { LoginService } from '../login/login.service';

@Injectable()
export class GradeService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/grades/';
  }

  public findAll(idExam: number) : Observable<Student[]>{
    let list = this.http.get<Student[]>(this.usersUrl + "exam/"+idExam, this.loginService.getHeaders());
    return list;
  }

  public saveAllGrades(idExam: number, students : Student[]){
    console.log("save all grades");
    return this.http.post<void>(this.usersUrl+"exams/"+idExam, students, this.loginService.getHeaders());
  }

  public saveGrade(idExam: number, student : Student){
    console.log("save grade of student "+student.idStudent);
    return this.http.post<void>(this.usersUrl+"exam/"+idExam,student, this.loginService.getHeaders());
  }

  public getHttp() :HttpClient {
    return this.http;
  } 

}
