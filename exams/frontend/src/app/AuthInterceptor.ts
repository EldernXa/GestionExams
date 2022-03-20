import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { catchError, Observable, of, throwError } from "rxjs";
import { LoginService } from "./service/login/login.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private router: Router, private loginService: LoginService){}

    private handleAuthError(err: HttpErrorResponse): Observable<any>{
        if(err.status === 401 || err.status === 403 || err.status === 410){
            this.loginService.logout();
            return of(err.message);
        }
        return throwError(err);
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authReq = req.clone({headers: this.loginService.getHeaders().headers});
        const skipIntercept = req.headers.has("skip");
        if(skipIntercept){
            req = req.clone({
                headers: req.headers.delete('skip')
            });
            return next.handle(req);
        }

        return next.handle(authReq).pipe(catchError(x=> this.handleAuthError(x)));
    }
}
