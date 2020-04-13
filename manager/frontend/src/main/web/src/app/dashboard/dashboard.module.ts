import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthGuardService as AuthGuard} from "../auth/auth-guard.service";
import {AccountsComponent} from "./accounts/accounts.component";
import {HelpComponent} from "./help/help.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

const routes: Routes = [
  {path: '', component: AccountsComponent},
  {path: 'accounts', component: AccountsComponent},
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
  ],
  exports: [],
  providers: [
    AuthGuard
  ]
})
export class DashboardModule { }
