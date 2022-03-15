import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";
import {Observable} from "rxjs";
import {Student} from "../../model/student/student";
import {Ue} from "../../model/ue/ue";
import {Inscription} from "../../model/inscription/inscription";

@Injectable()
export class InscriptionsService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/inscription/';
  }

  public findAll() : Observable<Inscription[]>{
    let list = this.http.get<Inscription[]>(this.usersUrl + "all", this.loginService.getHeaders());
    return list;
  }

  public getHttp() :HttpClient {
    return this.http;
  }

}
