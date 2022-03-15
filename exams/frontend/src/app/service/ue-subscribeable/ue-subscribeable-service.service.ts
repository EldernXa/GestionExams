import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../login/login.service";
import {Observable} from "rxjs";
import {Ue} from "../../model/ue/ue";
import {Inscription} from "../../model/inscription/inscription";

@Injectable()

export class UeSubscribeableService {

  private usersUrl: string;
  private ueUrl: string;
  private inscriptionUrl

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/ue/';
    this.ueUrl = 'http://localhost:8080/ue/';
    this.inscriptionUrl = 'http://localhost:8080/inscription/';
  }

  public findAllUe() : Observable<Ue[]>{
    let list = this.http.get<Ue[]>(this.ueUrl + "allUE", this.loginService.getHeaders());
    return list;
  }

  public findAllInscriptions() : Observable<Inscription[]>{
    let list = this.http.get<Inscription[]>(this.inscriptionUrl + "all", this.loginService.getHeaders());
    return list;
  }

  public subscribeToUe(year: number, ue: Ue){
    return this.http.post<Inscription>(this.inscriptionUrl + "register/"+year+"/"+ue.name,ue, this.loginService.getHeaders());
  }

  public getHttp() :HttpClient {
    return this.http;
  }
}
