import {FormBuilder, FormGroup, Validators} from "@angular/forms";
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
  loginForm: FormGroup;
  isSubmitted: boolean;
  hasErrors: boolean;
  errorMsg: string;

  constructor(private auth: AuthService, private router: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.auth.init().subscribe();
  }

  get formControls() {
    return this.loginForm.controls;
  }

  signIn() {
    this.isSubmitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    this.auth.login(this.loginForm.value)
    .subscribe(
      data => {
        this.router.navigate(["/dashboard"]);
      },
      errorResponse => {
        this.hasErrors = true;
        this.errorMsg = errorResponse.error.message;
      });
  }
}
