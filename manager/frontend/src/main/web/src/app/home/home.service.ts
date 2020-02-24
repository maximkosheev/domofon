import {Injectable, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AppSettings} from "../app-settings";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";

@Injectable()
export class HomeService {

  constructor (private http: HttpClient) {}

  version(): Observable<any> {
    return this.http.get(AppSettings.API_ENDPOINT + "/home/version");
  }
}
