import { Inject, Injectable, LOCALE_ID } from '@angular/core';
import {NgbDatepickerI18n, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

const I18N_VALUES = {
  'en-US': {
    weekdays: ['Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'],
    months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
  },
  'ru-RU': {
    weekdays: ['Пон', 'Вт', 'Ср', 'Чет', 'Пят', 'Сб', 'Вс'],
    months: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн', 'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек'],
  }
};

@Injectable()
export class CustomDatepickerI18n extends NgbDatepickerI18n {

  constructor(@Inject(LOCALE_ID) private locale: string) {
    super();
  }

  getWeekdayShortName(weekday: number): string {
    return I18N_VALUES[this.locale].weekdays[weekday - 1];
  }

  getMonthShortName(month: number): string {
    return I18N_VALUES[this.locale].months[month - 1];
  }

  getMonthFullName(month: number): string {
    return this.getMonthShortName(month);
  }

  getDayAriaLabel(date: NgbDateStruct): string {
    return `${date.day}-${date.month}-${date.year}`;
  }
}
