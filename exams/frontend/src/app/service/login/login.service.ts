import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  constructor(private http: HttpClient, private router: Router) {
  }


  redirectIfNotLogin(){
    if(!this.isConnected()){
      this.moveOnLoginPage();
    }
  }

  getRole(){
    return localStorage.getItem("role");
  }

  getName(): Observable<string>{
    return this.http.get<string>('http://localhost:11003/loggedMessage', {responseType: 'text' as 'json'});
  }

  redirectIfLogin(){
    if(this.isConnected()){
      this.moveOnIndexPage();
    }
  }

  isConnected(){
    if(localStorage.getItem("role")==null){
      return false;
    }
    return true;
  }

  moveOnLoginPage(){
    this.router.navigateByUrl("/login");
  }

  moveOnIndexPage(){
    window.location.href = window.location.protocol + '//' + window.location.host + '/'
  }

  logout(){
    localStorage.clear();
    window.location.href = window.location.protocol + '//' + window.location.host + '/login';
  }

  loginToken(ident: string, mdp: string){
    let params = new HttpParams().set("email", ident).set("password", mdp);

    return this.http.post<string>('http://localhost:11003/login', params, {headers:{skip:"true"}, responseType: 'text' as 'json'})
  }

  loginRole(data: string){
    localStorage.setItem("token", JSON.parse(data).access_token);
    return this.http.put<string>('http://localhost:11003/loginRole', {}, {headers: this.getHeadersForLogin().headers, responseType:'text' as 'json'});
  }

  getHeadersForLogin(){
    return {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + String(localStorage.getItem("token")),
        'skip': "true"
      })
    };
  }

  getHeaders(){
    if(this.isConnected()){
      return {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Access-Control-Allow-Origin': '*',
          'Authorization': 'Bearer ' + String(localStorage.getItem("token"))
        })
      };
    }

    return {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Access-Control-Allow-Origin': '*'
      })
    };
  }

}
