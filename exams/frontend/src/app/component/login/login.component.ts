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

  constructor(private route: ActivatedRoute,
    private router: Router, private http: HttpClient, private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.verifyIfIsConnected();
  }

  onSubmit(){
    this.loginService.login(this.ident, this.mdp);
  }

}
