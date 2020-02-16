import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {CustomEncoder} from "./custom-encoder";
import {map} from 'rxjs/operators';
import { AppSettings} from "../app-settings";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
    //
  }

  login(username: string, password: string, rememberMe: boolean) {
    let requestBody: any = {};
    requestBody.username = username;
    requestBody.password = password;
    requestBody.rememberMe = rememberMe;

    return this.http.post(AppSettings.API_ENDPOINT + "/auth/login", JSON.stringify(requestBody), {
      headers: new HttpHeaders().set('Content-Type', 'application/json'), observe: 'response'
    }).pipe(
      map((response) => {
        console.log(response);
        let jwtToken = response.body['token'];
        if (jwtToken) {
          localStorage.setItem("jwtToken", jwtToken)
        }
      })
    );
  }

  /**
   * Возвращает true, если пользователь прошел аутентификацию
   * @returns true/false
   */
  public isAuthenticated(): boolean {
    return localStorage.getItem("jwtToken") != null;
  }
}
