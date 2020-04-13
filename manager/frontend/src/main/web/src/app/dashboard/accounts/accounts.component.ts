import {AfterViewInit, Component, OnInit} from "@angular/core";
import {Account} from "../../domain/Account";
import {AccountsService} from "./accounts.service";
import {HttpErrorResponse} from "@angular/common/http";
import * as Feather from 'feather-icons';

@Component({
  selector: "app-accounts",
  templateUrl: "./accounts.component.html",
  styleUrls: ['./accounts.component.scss'],
  providers: [AccountsService]
})
export class AccountsComponent implements OnInit, AfterViewInit {

  accounts: Account[];
  total: Number;
  page: Number = 1;
  itemsPerPage:Number = 25;
  apiError: string;

  constructor(private accountsService: AccountsService) {
    //
  }

  ngOnInit(): void {
    this.accountsService.getClients()
    .subscribe(
      data => {
        this.accounts = data;
        this.total = this.accounts.length;
      },
      (error: HttpErrorResponse) => {
        this.apiError = error.message;
      }
    )
  }

  ngAfterViewInit(): void {
    Feather.replace();
  }
}
