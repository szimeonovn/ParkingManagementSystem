import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ButtonModule, DropdownModule, InputTextModule, ProgressSpinnerModule} from 'primeng/primeng';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppService} from './app.service';
import {FormsModule} from '@angular/forms';
import {ParkingTerminalComponent} from './parking-terminal/parking-terminal.component';
import {HttpClientModule} from '@angular/common/http';
import {SelectButtonModule} from 'primeng/components/selectbutton/selectbutton';
import {InputMaskModule} from 'primeng/components/inputmask/inputmask';
import {GrowlModule} from 'primeng/components/growl/growl';
import {DialogModule} from 'primeng/components/dialog/dialog';
import {MessageService} from 'primeng/components/common/messageservice';
import {ContextMenuModule} from 'primeng/components/contextmenu/contextmenu';
import {AuthenticationService} from './authentication.service';
import {HttpModule} from '@angular/http';
import {AppRoutingModule} from './/app-routing.module';
import {AdminComponent} from './admin/admin.component';
import {AuthGuard} from './guards/auth-guard';
import {LoginComponent } from './login/login.component';
import {DataTableModule} from 'primeng/primeng';


@NgModule({
  declarations: [
    AppComponent,
    ParkingTerminalComponent,
    AdminComponent,
    LoginComponent
  ],
  imports: [
    HttpClientModule,
    HttpModule,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    ProgressSpinnerModule,
    InputTextModule,
    FormsModule,
    DropdownModule,
    SelectButtonModule,
    InputMaskModule,
    GrowlModule,
    DialogModule,
    ContextMenuModule,
    AppRoutingModule,
    DataTableModule
  ],
  providers: [
    AppService,
    MessageService,
    AuthenticationService,
    AuthGuard],
  bootstrap: [AppComponent]
})

export class AppModule {
}
