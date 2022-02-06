import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ue} from "../../model/ue/ue";

@Injectable()

export class UeService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/ue/';
  }

  public findAll() : Observable<Ue[]>{
    let list = this.http.get<Ue[]>(this.usersUrl + "allUE");
    return list;
  }

  public addUe(ue: Ue){
    return this.http.post<Ue>(this.usersUrl + "add", ue);
  }

  public updateUe(ue_name : string , ue: Ue){
    return this.http.post<Ue>(this.usersUrl + "update/" + ue_name, ue);
  }

  public getUe(ue_name: string): Observable<Ue>{
    return this.http.get<Ue>(this.usersUrl + ue_name);
  }

  public deleteUe(ue_name: string){
    return this.http.get<void>(this.usersUrl + ue_name);
  }

  public getHttp() :HttpClient {
    return this.http;
  }
}
