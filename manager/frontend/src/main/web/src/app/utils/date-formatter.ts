import {NgbDateParserFormatter, NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import * as moment from 'moment';

export class DateFormatter extends NgbDateParserFormatter {

  readonly DEFAULT_FORMAT = 'DD.MM.YYYY';
  readonly dt_format: string = this.DEFAULT_FORMAT;

  constructor(format: string) {
    super();
    if (format) {
      this.dt_format = format;
    }
  }

  format(date: NgbDateStruct | null): string {
    if (!date) return '';
    let mdt = moment([date.year, date.month - 1, date.day]);
    if (!mdt.isValid()) return '';
    return mdt.format(this.dt_format);
  }

  parse(value: string): NgbDateStruct | null {
    if (value) {
      value = value.trim();
      let mdt = moment(value, this.dt_format);
      return {year: mdt.year(), month: mdt.month() + 1, day: mdt.date()};
    } else {
      return null;
    }
  }
}
