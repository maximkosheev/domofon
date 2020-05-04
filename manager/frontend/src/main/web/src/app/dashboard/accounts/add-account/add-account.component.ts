import {Component, LOCALE_ID, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountsService} from "../accounts.service";
import {CommonService} from "../../common.service";
import {City} from "../../../domain/City";
import {Street} from "../../../domain/Street";
import {NgbDate, NgbDateParserFormatter, NgbDatepickerI18n} from "@ng-bootstrap/ng-bootstrap";
import {CustomDatepickerI18n} from "../../../utils/datepicker-i18n";
import {DateFormatter} from "../../../utils/date-formatter";
import {HttpErrorResponse} from "../../../../../node_modules/@angular/common/http";
import {Account} from "../../../domain/Account";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.scss'],
  providers: [
    AccountsService,
    CommonService,
    {
      provide: NgbDatepickerI18n,
      useClass: CustomDatepickerI18n
    },
    {
      provide: NgbDateParserFormatter,
      useValue: new DateFormatter('DD.MM.YYYY')
    }
  ]
})
export class AddAccountComponent implements OnInit {

  apiError: string;
  apiSuccess: string;
  accountForm: FormGroup;
  submitted = false;
  cities: City[] = [];
  allStreets: Street[] = [];
  cityStreets: Street[] = [];

  constructor(
    private fb: FormBuilder,
    private accountService: AccountsService,
    private commonService: CommonService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  get f() {
    return this.accountForm.controls;
  }

  ngOnInit(): void {
    this.commonService.getStreets().subscribe(
      (value => {
        let cities = value.map((street) => street.city);
        const map = new Map();
        for (const city of cities) {
          if (!map.has(city.id)) {
            map.set(city.id, true);
            this.cities.push(city);
          }
        }
        this.allStreets = value;
      })
    );
    let today = new Date();
    this.accountForm = this.fb.group({
      account: ['', Validators.required],
      city: [null, Validators.required],
      street: [null, Validators.required],
      house: [null, Validators.required],
      letter: '',
      building: '',
      porch: '',
      flat: '',
      fio: '',
      hasDevice: true,
      switchOff: false,
      connectDate: [new NgbDate(today.getFullYear(), today.getMonth() + 1, today.getDate()), Validators.nullValidator],
      phone: '',
      description: '',
      fsb: true,
      gorod: true
    });
  }

  get city() {
    return this.accountForm.get('city').value;
  }

  resetStreet() {
    this.accountForm.get('street').reset(null);
  }

  onCityChanged() {
    this.cityStreets = this.allStreets.filter((street) => {return street.city.id == this.city});
    this.resetStreet();
  }

  onSubmit() {
    this.submitted = true;

    if (this.accountForm.invalid) {
      return;
    }

    this.accountService.addAccount(this.accountForm.value).subscribe(
      data => {
        this.apiSuccess = 'Новый клиент успешно создан';
        this.apiError = null;
      },
      (error: HttpErrorResponse) => {
        if (error.error.message) {
          this.apiSuccess = null;
          this.apiError = error.error.message;
        }
    });
  }

}
