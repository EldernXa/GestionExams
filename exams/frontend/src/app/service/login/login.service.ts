import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  /*private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Origin': '*',
      'Authorization': 'Basic ' + btoa(localStorage.getItem('ident')+':' + localStorage.getItem('mdp'))
    })
  };*/

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Origin': '*',
      'Authorization': 'Bearer ' + String(localStorage.getItem("token"))
    })
  };


  redirectIfNotLogin(){
    if(!this.isConnected()){
      this.moveOnLoginPage();
    }
  }
  
  getRole(){
    console.log("okok");
    return localStorage.getItem("role");
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

    let params = new HttpParams().set("email", ident).set("password", mdp);

    this.http.put<string>('http://localhost:8080/login', params, {responseType: 'text' as 'json'})
        .subscribe((data)=>{
          localStorage.setItem("token", JSON.parse(data).access_token);

          this.http.put<string>('http://localhost:8080/loginRole', {}, {headers: this.getHeaders().headers, responseType:'text' as 'json'})
            .subscribe((data) => {
              localStorage.setItem("role", data);
              this.moveOnIndexPage();
            });

        });
  }

  getHeaders(){
    return this.httpOptions;
  }

}
