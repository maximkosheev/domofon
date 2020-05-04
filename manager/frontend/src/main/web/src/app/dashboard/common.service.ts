import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppSettings} from "../app-settings";
import {Street} from "../domain/Street";

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private httpClient: HttpClient) { }

  getStreets(): Observable<Street[]> {
    return this.httpClient.get<Street[]>(AppSettings.API_ENDPOINT + "/common/streets", {responseType: "json"});
  }
}
