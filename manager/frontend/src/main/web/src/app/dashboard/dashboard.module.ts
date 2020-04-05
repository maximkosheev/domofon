import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthGuardService as AuthGuard} from "../auth/auth-guard.service";
import {AccountsComponent} from "./accounts/accounts.component";
import {HelpComponent} from "./help/help.component";

const routes: Routes = [
  {path: '', component: AccountsComponent},
  {path: 'accounts', component: AccountsComponent},
  {path: 'help', component: HelpComponent},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  declarations: [
    AccountsComponent,
    HelpComponent
  ],
  exports: [],
  providers: [
    AuthGuard
  ]
})
export class DashboardModule { }
