import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AuthComponent implements OnInit {
  model: any = {};

  constructor(
    public auth: AuthService,
    public router: Router
  ) {
  }

  ngOnInit() {
    this.model.rememberMe = true;
  }

  signin() {
    this.auth.login(this.model.username, this.model.password, this.model.rememberMe)
    .subscribe(
      data => {
        this.router.navigate(["/dashboard"]);
      },
      error => {
        //
      });
  }
}
