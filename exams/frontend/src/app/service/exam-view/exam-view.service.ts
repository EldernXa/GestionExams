import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exam } from 'src/app/model/exam/exam';
import { LoginService } from '../login/login.service';

@Injectable()
export class ExamViewService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/student/';
  }

  public findAllNextExamForAStudent():Observable<Exam[]>{
    return this.http.get<Exam[]>(this.usersUrl + "exams", this.loginService.getHeaders());
  }
}
