import {AfterViewInit, Component, OnInit} from "@angular/core";
import * as Feather from 'feather-icons';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit{
  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    Feather.replace();
  }
}
