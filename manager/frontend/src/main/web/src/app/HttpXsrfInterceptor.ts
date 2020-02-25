import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpXsrfTokenExtractor} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class HttpXsrfInterceptor implements HttpInterceptor {

  constructor(private tokenExtractor: HttpXsrfTokenExtractor) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const headerName = 'X-XSRF-TOKEN';
    let requestMethod: string = req.method.toLowerCase();

    if (requestMethod && (requestMethod === 'post' || requestMethod === 'delete' || requestMethod === 'put')) {
      req = req.clone({withCredentials: true});
      let token = this.tokenExtractor.getToken();
      if (token !== null && !req.headers.has(headerName)) {
        req = req.clone({
          headers: req.headers.set(headerName, token),
        });
      }
    }
    return next.handle(req);
  }
}
