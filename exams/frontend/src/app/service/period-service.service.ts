import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Period } from '../model/period';
import { map, Observable } from 'rxjs';

@Injectable()
export class PeriodService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/';
  }

  public findAll() : Observable<Period[]>{
    this.http.get(this.usersUrl+"periodList/1/beginDate", {responseType: 'text'}).subscribe(data=>{
      console.log(data);
    });

    let list = this.http.get<Period[]>(this.usersUrl + "periodList");

    return list;
  }

  public getHttp() :HttpClient {
    return this.http;
  }
}
