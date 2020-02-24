import { Component, OnInit } from '@angular/core';
import {ClientsService} from "./clients.service";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  constructor(
    private service: ClientsService
  ) { }

  ngOnInit() {
    this.service.clients().subscribe(
      value => {console.log(value)},
      error => {console.log(error)}
    )
  }

}
