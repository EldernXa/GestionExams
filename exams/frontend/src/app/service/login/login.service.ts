import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Origin': '*',
      'Authorization': 'Basic ' + btoa('emailSchool1:password21')
    })
  };

  verifyIfLogin(){
    if(!this.isConnected()){
      this.moveOnLoginPage();
    }
  }

  verifyIfIsConnected(){
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
    window.location.href = window.location.protocol + '//' + window.location.host + '/login'
  }

  moveOnIndexPage(){
    window.location.href = window.location.protocol + '//' + window.location.host + '/'
  }

  logout(){
    localStorage.clear();
    window.location.href = window.location.protocol + '//' + window.location.host + '/login';
  }

  login(ident: string, mdp: string){
    const map = {
      'username' : ident,
      'mdp' : mdp,
    }

    this.http.put<string>('http://localhost:8080/login', map, {responseType: 'text' as 'json'})
        .subscribe((data)=>{
          localStorage.setItem("ident", ident);
          localStorage.setItem("mdp", mdp);
          localStorage.setItem("role", data);
          this.moveOnIndexPage();
        });
  }

  getHeaders(){
    return this.httpOptions;
  }

}
