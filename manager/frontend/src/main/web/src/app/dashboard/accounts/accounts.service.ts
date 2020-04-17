import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {AppSettings} from "../../app-settings";
import {Observable} from "rxjs";
import {PageRequest} from "../../domain/PageRequest";
import {PageResponse} from "../../domain/PageResponse";
import {Account} from "../../domain/Account";

@Injectable()
export class AccountsService {

  constructor(private httpClient: HttpClient) {
    //
  }

  protected getRequestFilterPredicate(pageable: PageRequest): HttpParams {
    let params: HttpParams = new HttpParams();
    for (let key of pageable.filter.keys()) {
      if ( pageable.filter.get(key) ) {
        params = params.set(key, pageable.filter.get(key));
      }
    }
    params = params.set("page", pageable.page.toString());
    params = params.set("pageSize", pageable.pageSize.toString());
    if (pageable.sortBy) {
      params = params.set("sortBy", pageable.sortBy);
      if (pageable.direction) {
        params = params.set("direction", pageable.direction);
      }
    }
    return params;
  }

  getClients(pageable:PageRequest): Observable<PageResponse<Account>> {
    return this.httpClient.get<PageResponse<Account>>(AppSettings.API_ENDPOINT + "/accounts/all",
      {
        params: this.getRequestFilterPredicate(pageable),
        responseType: "json"
      }
    );
  }
}
