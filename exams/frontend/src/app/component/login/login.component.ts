import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, of, retryWhen, shareReplay, tap, throwError } from 'rxjs';
import { LoginService } from 'src/app/service/login/login.service';
import { PeriodService } from 'src/app/service/period/period-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  ident: string = "";
  mdp: string = "";
  role: string = "";
  msg: string | null = "";

  constructor(private route: ActivatedRoute,
    private router: Router, private http: HttpClient, private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.redirectIfLogin();
  }

  onSubmit(){
    let canConnect: boolean = true;
    this.loginService.loginToken(this.ident, this.mdp).subscribe((data)=>{
      this.loginService.loginRole(data).pipe(
        catchError(error => {
          canConnect = false;
          switch(error.status){
            case 410 :{
              this.msg = "Erreur survenue lors de la vérification du token."
              break;
            }
            default:{
              this.msg = "Erreur inconnue."
            }
          }
          return of([]);
        })
    )
      .subscribe((data) => {
        if(canConnect){
          localStorage.setItem("role", String(data));
          this.loginService.moveOnIndexPage();
        }
      });;
    });
  }

}
