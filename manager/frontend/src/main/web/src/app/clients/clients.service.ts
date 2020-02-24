import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AppSettings} from "../app-settings";
import {Observable} from "rxjs";

@Injectable()
export class ClientsService {

  constructor(private http: HttpClient) {
  }

  clients(): Observable<any> {
    return this.http.get(AppSettings.API_ENDPOINT + "/clients");
  }
}
