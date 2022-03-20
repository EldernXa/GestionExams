import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";
import {Observable} from "rxjs";
import {Inscription} from "../../model/inscription/inscription";

@Injectable()
export class InscriptionsService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/inscription/';
  }

  public findAll() : Observable<Inscription[]>{
    return this.http.get<Inscription[]>(this.usersUrl + "all", this.loginService.getHeaders());
  }

  public getHttp() :HttpClient {
    return this.http;
  }

}
