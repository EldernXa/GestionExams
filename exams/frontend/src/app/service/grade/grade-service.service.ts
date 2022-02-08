import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Student } from '../../model/student/student';
import { map, Observable } from 'rxjs';

@Injectable()
export class GradeService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/grades/';
  }

  public findAll(idExam: number) : Observable<Student[]>{
    let list = this.http.get<Student[]>(this.usersUrl + "exam/"+idExam);
    return list;
  }

  public saveAllGrades(idExam: number, students : Student[]){
    console.log("save all grades");
    return this.http.post<void>(this.usersUrl+"exams/"+idExam, students);
  }

  public saveGrade(idExam: number, student : Student){
    console.log("save grade of student "+student.idStudent);
    return this.http.post<void>(this.usersUrl+"exam/"+idExam,student);
  }

  public getHttp() :HttpClient {
    return this.http;
  }

}
