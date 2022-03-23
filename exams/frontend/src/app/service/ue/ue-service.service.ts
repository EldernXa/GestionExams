import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ue} from "../../model/ue/ue";
import { LoginService } from '../login/login.service';

@Injectable()

export class UeService {

  private usersUrl: string;

  constructor(private http: HttpClient, private loginService:LoginService) {
    this.usersUrl = 'http://localhost:8080/ue/';
  }

  public findAll() : Observable<Ue[]>{
    return this.http.get<Ue[]>(this.usersUrl + "allUE", this.loginService.getHeaders());
  }

  public addUe(ue: Ue){
    return this.http.post<Ue>(this.usersUrl + "add", ue, this.loginService.getHeaders());
  }

  public updateUe(ue_name : string , ue: Ue){
    return this.http.put<Ue>(this.usersUrl + "update/" + ue_name, ue, this.loginService.getHeaders());
  }

  public getUe(ue_name: string): Observable<Ue>{
    return this.http.get<Ue>(this.usersUrl + ue_name, this.loginService.getHeaders());
  }

  public deleteUe(ue_name: string) {
    return this.http.delete<void>(this.usersUrl + ue_name, this.loginService.getHeaders()); // Not tested
  }

  public getHttp() :HttpClient {
    return this.http;
  }

  public isUeNameGood(ueName: string): Observable<boolean>{
    return this.http.get<boolean>(this.usersUrl + "isUeNameGood/"+ueName);
  }
}
