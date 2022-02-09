import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './service/login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  role: string = "";
  isLogin: boolean = false;

  constructor(private router:Router, private loginService: LoginService){
    this.title = 'ENT';
    if(this.loginService.isConnected()){
      this.isLogin = true;
    }
  }

  logout(){
    this.loginService.logout();
  }
}
