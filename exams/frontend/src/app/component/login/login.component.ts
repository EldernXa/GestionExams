import { Component, OnInit } from '@angular/core';
import { catchError, of} from 'rxjs';
import { LoginService } from 'src/app/service/login/login.service';

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

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.redirectIfLogin();
  }

  onSubmit(){
    let canConnect: boolean = true;
    this.loginService.loginToken(this.ident, this.mdp).pipe(
      catchError(error => {
        canConnect = false;
        if(error.status == 403){
          this.msg = "Mauvais identifiant ou mot de passe.";
        }else{
          this.msg = "Erreur inconnue lors de la tentative de connexion."
        }
        return of([]);
      })
    )
    .subscribe((dataToken)=>{
      if(canConnect){
        this.loginService.loginRole(dataToken.toString()).pipe(
          catchError(error => {
            canConnect = false;
            if(error.status == 410){
              this.msg = "Erreur survenue lors de la vérification du token."
            }else{
              this.msg = "Erreur inconnue lors de la tentative de connexion."
            }
            return of([]);
          })
      )
        .subscribe((data) => {
          if(canConnect){
            localStorage.setItem("role", String(data));
            this.loginService.moveOnIndexPage();
          }
        });
    }
    });
  }

}
