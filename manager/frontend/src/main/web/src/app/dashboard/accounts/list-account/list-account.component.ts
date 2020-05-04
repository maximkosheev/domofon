import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AccountsService} from "../accounts.service";
import {Account} from "../../../domain/Account";
import {Direction, PageRequest} from "../../../domain/PageRequest";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import * as Feather from 'feather-icons';

@Component({
  selector: 'app-list-account',
  templateUrl: './list-account.component.html',
  styleUrls: ['./list-account.component.scss'],
  providers: [AccountsService]
})
export class ListAccountComponent implements OnInit, AfterViewInit {
  accounts: Account[];
  pageRequest: PageRequest = {
    page: 1,
    pageSize: 25,
    sortBy: "account",
    direction: Direction.ASC,
    filter: new Map([
      ["account", null]
    ])
  };
  total: number;
  apiError: string;
  accountFilter: string;

  constructor(
    private accountsService: AccountsService,
    private router: Router,
    private route: ActivatedRoute) {
    //
  }

  loadAccounts() {
    this.pageRequest.filter.set("account", this.accountFilter);
    this.accountsService.getAccounts(this.pageRequest)
    .subscribe(
      data => {
        this.accounts = data.content;
        this.total = data.totalElements;
      },
      (error: HttpErrorResponse) => {
        this.apiError = error.message;
      }
    )
  }

  addAccount() {
    this.router.navigate(['add'], {relativeTo: this.route.parent});
  }

  ngOnInit(): void {
    this.loadAccounts();
  }

  ngAfterViewInit(): void {
    Feather.replace();
  }
}
