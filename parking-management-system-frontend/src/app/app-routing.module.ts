import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ParkingTerminalComponent} from './parking-terminal/parking-terminal.component';
import {AdminComponent} from './admin/admin.component';
import {AuthGuard} from './guards/auth-guard';
import {LoginComponent} from './login/login.component';

const routes: Routes = [
  {path: '', redirectTo: '/terminal', pathMatch: 'full'},
  {path: 'terminal', component: ParkingTerminalComponent},
  {path: 'login', component: LoginComponent},
  {path : 'admin', component: AdminComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
