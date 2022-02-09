import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Period } from '../../model/period/period';
import { map, Observable } from 'rxjs';
import { LoginService } from '../login/login.service';

@Injectable()
export class PeriodService {

  private usersUrl: string;
  

  constructor(private http: HttpClient, private loginService: LoginService) {
    this.usersUrl = 'http://localhost:8080/';
  }

  public findAll() : Observable<Period[]>{
    let list = this.http.get<Period[]>(this.usersUrl + "periodList", this.loginService.getHeaders());

    return list;
  }

  public savePeriod(period: Period){
    return this.http.post<Period>(this.usersUrl+"period", period);
  }

  public getPeriod(index: number): Observable<Period>{
    return this.http.get<Period>(this.usersUrl + "period/" + index, this.loginService.getHeaders());
  }

  public getHttp() :HttpClient {
    return this.http;
  }

  public getHeaders():HttpHeaders {
    return this.loginService.getHeaders().headers;
  }
}
