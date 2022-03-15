import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";
import {Observable} from "rxjs";
import {Inscription} from "../../model/inscription/inscription";
import {Grade} from "../../model/grade/grade";

@Injectable()
export class GradesViewService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/grades/';
  }

  public findAll() : Observable<Grade[]>{
    let list = this.http.get<Grade[]>(this.usersUrl + "student", this.loginService.getHeaders());
    return list;
  }

  public getHttp() :HttpClient {
    return this.http;
  }

}

