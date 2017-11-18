import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ButtonModule, InputTextModule, ProgressSpinnerModule} from 'primeng/primeng';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpModule} from '@angular/http';
import {AppService} from './app.service';
import {FormsModule} from '@angular/forms';
import {ParkingTerminalComponent} from './parking-terminal/parking-terminal.component';

@NgModule({
  declarations: [
    AppComponent,
    ParkingTerminalComponent
  ],
  imports: [
    HttpModule,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    ProgressSpinnerModule,
    InputTextModule,
    FormsModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})

export class AppModule {
}
