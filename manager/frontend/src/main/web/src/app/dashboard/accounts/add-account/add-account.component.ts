import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.scss']
})
export class AddAccountComponent implements OnInit {

  apiError: string;
  accountForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.accountForm = this.fb.group({
      account: '',
      street: '',
      house: '',
      letter: '',
      building: '',
      porch: '',
      flat: '',
      fio: '',
      hasDevice: true,
      connectDate: Date.now(),
      phone: '',
      description: '',
      fsb: true,
      gorod: true
    });
  }

  get formControls() {
    return this.accountForm.controls;
  }

  ngOnInit(): void {
  }

}
