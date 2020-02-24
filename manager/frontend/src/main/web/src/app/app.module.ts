import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthComponent} from './auth/auth.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {HomeComponent} from './home/home.component';
import {AuthService} from './auth/auth.service';
import {DashboardModule} from './dashboard/dashboard.module';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {HomeService} from "./home/home.service";
import {ClientsService} from "./clients/clients.service";
import {HttpXsrfInterceptor} from "./HttpXsrfInterceptor";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AuthComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    DashboardModule,
    HttpClientXsrfModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpXsrfInterceptor, multi: true},
    AuthService,
    HomeService,
    ClientsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
