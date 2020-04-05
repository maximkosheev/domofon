import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {AppSettings} from "../../app-settings";
import {Observable} from "rxjs";
import {Account} from "../../domain/Account";
import {map} from "rxjs/operators";

@Injectable()
export class AccountsService {

  constructor(private httpClient: HttpClient) {
    //
  }

  getClients(): Observable<Account[]> {
    return this.httpClient.get<Account[]>(AppSettings.API_ENDPOINT + "/accounts/all",
      {
        params: new HttpParams()
        .set("account", "1"),
        responseType: "json"
      }
    ).pipe(map(
      (response: Account[]) => {
        for (let i = 0; i < response.length; i++) {
          response[i].createDate = response[i].createDate != null ? new Date(response[i].createDate) : null;
          response[i].connectDate = response[i].connectDate != null ? new Date(response[i].connectDate) : null;
          response[i].disconnectDate = response[i].disconnectDate != null ? new Date(response[i].disconnectDate) : null;
        }
        return response;
      }
    ));
  }
}
