import {Component, OnInit} from "@angular/core";
import {Account} from "../../domain/Account";
import {AccountsService} from "./accounts.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: "app-accounts",
  templateUrl: "./accounts.component.html",
  styleUrls: ['./accounts.component.css'],
  providers: [AccountsService]
})
export class AccountsComponent implements OnInit {

  accounts: Account[];
  apiError: string;

  constructor(private accountsService: AccountsService) {
    //
  }

  ngOnInit(): void {
    this.accountsService.getClients()
    .subscribe(
      data => {
        this.accounts = data;
      },
      (error: HttpErrorResponse) => {
        this.apiError = error.message;
      }
    )
  }
}
