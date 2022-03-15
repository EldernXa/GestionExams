import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { catchError, Observable, of, skip, throwError } from "rxjs";
import { LoginService } from "./service/login/login.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private router: Router, private loginService: LoginService){}

    private handleAuthError(err: HttpErrorResponse): Observable<any>{
        if(err.status === 401 || err.status === 403 || err.status === 410){
            this.loginService.logout();
            //this.loginService.moveOnLoginPage();
            return of(err.message);
        }
        return throwError(err);
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // Clone the request to add the new header.
        const authReq = req.clone({headers: this.loginService.getHeaders().headers});
        const skipIntercept = req.headers.has("skip");
        if(skipIntercept){
            req = req.clone({
                headers: req.headers.delete('skip')
            });
            return next.handle(req);
        }
        
        // catch the error, make specific functions for catching specific errors and you can chain through them with more catch operators
        return next.handle(authReq).pipe(catchError(x=> this.handleAuthError(x))); //here use an arrow function, otherwise you may get "Cannot read property 'navigate' of undefined" on angular 4.4.2/net core 2/webpack 2.70
    }
}