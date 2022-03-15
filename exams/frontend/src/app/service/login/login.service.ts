import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private router: Router) { }

  /*private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Origin': '*',
      'Authorization': 'Basic ' + btoa(localStorage.getItem('ident')+':' + localStorage.getItem('mdp'))
    })
  };*/


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
    this.router.navigateByUrl("/login");
    //window.location.href = window.location.protocol + '//' + window.location.host + '/login'
  }

  moveOnIndexPage(){
    window.location.href = window.location.protocol + '//' + window.location.host + '/'
  }

  logout(){
    localStorage.clear();
    window.location.href = window.location.protocol + '//' + window.location.host + '/login';
  }

  loginToken(ident: string, mdp: string){
    let canConnect: boolean = true;
    let params = new HttpParams().set("email", ident).set("password", mdp);

    return this.http.post<string>('http://localhost:8080/login', params, {headers:{skip:"true"}, responseType: 'text' as 'json'})
        /*.subscribe((data)=>{
          localStorage.setItem("token", JSON.parse(data).access_token);

          this.http.put<string>('http://localhost:8080/loginRole', {}, {headers: this.getHeaders().headers, responseType:'text' as 'json'})
            .pipe(
              catchError(error => {
                canConnect = false;
                  if (error.error instanceof ErrorEvent) {
                      console.log("test1");
                      //this.errorMsg = `Error: ${error.error.message}`;
                  } else {
                      console.log("test2");
                      //this.errorMsg = `Error: ${error.message}`;
                  }
                  return of([]);
              })
          )
            .subscribe((data) => {
              if(canConnect){
                localStorage.setItem("role", String(data));
                this.moveOnIndexPage();
              }
            });

        });*/
  }

  loginRole(data: string){
    localStorage.setItem("token", JSON.parse(data).access_token);
    return this.http.put<string>('http://localhost:8080/loginRole', {}, {headers: this.getHeadersForLogin().headers, responseType:'text' as 'json'});
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
