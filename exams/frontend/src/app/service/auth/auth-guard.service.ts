import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router } from "@angular/router";
import { LoginService } from "../login/login.service";

@Injectable()
export class AuthGuard implements CanActivate{
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const roleNeeded = route.data['role'];
        if(this.loginService.getRole() != roleNeeded){
            this.router.navigate(['/']);
        }
        return true;
    }

    constructor(private router: Router, private loginService: LoginService) { }
}