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
    let list = this.http.get<Period[]>(this.usersUrl + "periodList");

    return list;
  }

  public savePeriod(period: Period){
    return this.http.post<Period>(this.usersUrl+"period", period);
  }

  public getPeriod(index: number): Observable<Period>{
    return this.http.get<Period>(this.usersUrl + "period/" + index);
  }

  public isPeriodDateGood(dateBegin: string, dateEnd: string){
    return this.http.get(this.usersUrl+"verifyDatePeriod/"+dateBegin+"/"+dateEnd);
  }

  public isPeriodNameGood(namePeriod: string){
    return this.http.get(this.usersUrl + "verifyNamePeriod/" + namePeriod);
  }

  public deletePeriod(idPeriod: number){
    return this.http.delete(this.usersUrl + "period/" + idPeriod);
  }

  public isDisabled(idPeriod: number): Observable<boolean>{
    return this.http.get<boolean>(this.usersUrl+"period/verifyPlanify/"+idPeriod);
  }

}
