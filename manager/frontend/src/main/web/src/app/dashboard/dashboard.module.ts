import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { ClientsComponent } from '../clients/clients.component';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { NavbarComponent } from '../navbar/navbar.component';

const routes: Routes = [
    {path: '', component: ClientsComponent},
    {path: 'clients', component: ClientsComponent},
    {path: '**', redirectTo: ''}
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        FormsModule,
        NgbDropdownModule,
    ],
    declarations:[
        SidebarComponent,
        NavbarComponent,
        ClientsComponent,
    ],
    exports: [
        SidebarComponent,
        NavbarComponent
    ]
})
export class DashboardModule {}
