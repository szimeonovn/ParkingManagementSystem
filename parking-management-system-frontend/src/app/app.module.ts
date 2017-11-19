import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ButtonModule, DropdownModule, InputTextModule, ProgressSpinnerModule} from 'primeng/primeng';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppService} from './app.service';
import {FormsModule} from '@angular/forms';
import {ParkingTerminalComponent} from './parking-terminal/parking-terminal.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    ParkingTerminalComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    ProgressSpinnerModule,
    InputTextModule,
    FormsModule,
    DropdownModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})

export class AppModule {
}
