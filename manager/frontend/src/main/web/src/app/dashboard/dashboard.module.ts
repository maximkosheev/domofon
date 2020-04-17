import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthGuardService as AuthGuard} from "../auth/auth-guard.service";
import {AccountsComponent} from "./accounts/accounts.component";
import {HelpComponent} from "./help/help.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import { AddAccountComponent } from './accounts/add-account/add-account.component';
import { ListAccountComponent } from './accounts/list-account/list-account.component';
import {DashboardComponent} from "./dashboard.component";

const routes: Routes = [
  {path: 'accounts', component: AccountsComponent,
    children: [
      {path: '', redirectTo: 'list'},
      {path: 'list', component: ListAccountComponent},
      {path: 'add', component: AddAccountComponent}
    ]},
  {path: 'help', component: HelpComponent},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes),
    NgbModule
  ],
  declarations: [
    AccountsComponent,
    HelpComponent,
    AddAccountComponent,
    ListAccountComponent,
  ],
  exports: [],
  providers: [
    AuthGuard
  ]
})
export class DashboardModule { }
