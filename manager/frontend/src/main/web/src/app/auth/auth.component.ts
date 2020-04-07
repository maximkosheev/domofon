import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AuthComponent implements OnInit {
  model: any = {};
  submitted: boolean = false;
  hasError: boolean = false;
  errorMsg: string = '';

  constructor(private auth: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.model.rememberMe = true;
    this.auth.init().subscribe();
  }

  signin() {
    this.submitted = true;
    this.hasError = false;

    this.auth.login(this.model.username, this.model.password, this.model.rememberMe)
    .subscribe(
      data => {
        this.router.navigate(["/dashboard"]);
      },
      errorResponse => {
        this.hasError = true;
        this.errorMsg = errorResponse.error.msg;
      });
  }
}
