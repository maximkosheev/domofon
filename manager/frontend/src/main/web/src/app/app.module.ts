import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthComponent} from './auth/auth.component';
import {AuthService} from './auth/auth.service';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpXsrfInterceptor} from "./HttpXsrfInterceptor";
import {HttpAuthInterceptor} from "./HttpAuthInterceptor";
import {ErrorInterseptor} from "./ErrorInterseptor";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {SidebarModule} from "./dashboard/sidebar/sidebar.module";

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    SidebarModule,
    HttpClientXsrfModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpXsrfInterceptor, multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterseptor, multi: true},
    AuthService,
    {provide: LOCALE_ID, useValue: 'ru-RU'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
