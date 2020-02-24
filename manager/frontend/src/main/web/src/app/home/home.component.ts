import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {HomeService} from "./home.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {

  version: string = '';

  constructor(
    private homeService: HomeService) {
  }

  ngOnInit() {
    this.homeService.version().subscribe(
      value => {
        this.version = value.version;
      },
      error => {
        console.log(error);
        this.version = 'Error';
      }
    )
  }

}
