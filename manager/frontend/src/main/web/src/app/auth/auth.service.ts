import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map} from 'rxjs/operators';
import { AppSettings} from "../app-settings";
import {AuthInfo} from "../domain/AuthInfo";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
    //
  }

  init() {
    return this.http.get(AppSettings.API_ENDPOINT + "/auth/login", {withCredentials: true});
  }

  login(authInfo: AuthInfo) {
    let requestBody: any = {};
    requestBody.username = authInfo.username;
    requestBody.password = authInfo.password;
    requestBody.rememberMe = true;

    return this.http.post(AppSettings.API_ENDPOINT + "/auth/login", JSON.stringify(requestBody), {
      headers: new HttpHeaders().set('Content-Type', 'application/json'), observe: 'response'
    }).pipe(
      map((response) => {
        let jwtToken = response.body['token'];
        if (jwtToken) {
          localStorage.setItem("jwtToken", jwtToken)
        }
      })
    );
  }

  logout() {
    localStorage.removeItem("jwtToken");
  }

  /**
   * Возвращает true, если пользователь прошел аутентификацию
   * @returns true/false
   */
  public isAuthenticated(): boolean {
    return localStorage.getItem("jwtToken") != null;
  }

  public getJwtToken(): string {
    return localStorage.getItem("jwtToken");
  }
}
