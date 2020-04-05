import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthComponent} from './auth/auth.component';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AuthGuardService as AuthGuard} from './auth/auth-guard.service';

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard], children: [
    {path: '', loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)}
  ]},
  {path: 'login', component: AuthComponent},
  {path: '**', redirectTo: 'dashboard'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
