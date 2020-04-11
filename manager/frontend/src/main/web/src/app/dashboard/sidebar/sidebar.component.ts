import {AfterViewInit, Component, OnInit} from '@angular/core';
import * as Feather from 'feather-icons';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/dashboard/accounts', title: 'Клиенты',  icon: 'users', class: '' },
  { path: '/dashboard/pay', title: 'Оплата', icon: 'dollar-sign', class: ''},
  { path: '/dashboard/help', title: 'Помощь',  icon:'help-circle', class: '' },
]; 

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit, AfterViewInit {
  menuItems: any[]; 
  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem); 
  }

  ngAfterViewInit(): void {
    Feather.replace();
  }

}
