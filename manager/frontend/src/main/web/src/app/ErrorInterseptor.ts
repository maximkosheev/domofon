import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {AuthService} from "./auth/auth.service";
import {catchError} from "rxjs/operators";

@Injectable()
export class ErrorInterseptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
    //
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError(err => {
        if (err.status === 401) {
          this.authService.logout();
          location.reload()
        }
        return throwError(err);
      })
    );
  }
}
