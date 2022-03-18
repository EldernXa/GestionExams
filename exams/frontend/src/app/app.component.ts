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
  role: string | null = "";
  isLogin: boolean = false;
  state: string = ""

  constructor(private router:Router, private loginService: LoginService){
    this.title = 'NotePlus';
    if(this.loginService.isConnected()){
      this.isLogin = true;
      this.role = this.loginService.getRole();
    }else{
      this.router.navigate(['/login']);
    }
  }

  logout(){
    this.loginService.logout();
  }

  setState(state: string){
    this.state = state;
  }

  isCurrentState(state: string): boolean{
    return (this.state == state);
  }

  getButtonState(state: string): string{
    if(this.isCurrentState(state))
      return "btn-light";
    return "btn-outline-light";
  }
}
