import {Component} from '@angular/core';
import {AppService} from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  response: any;
  isLoading: boolean;
  name: number;

  constructor(private appService: AppService) {
    this.isLoading = false;
  }

  test(): void {
    this.isLoading = true;
    const body = {zoneCode: this.name};
    console.log(body);
    this.appService.callRestPost('parkingZone/save', body)
      .then(response => {
        this.isLoading = false;
        this.response = response;
        console.log(response);
      }, () => {
        this.isLoading = false;
      });
  }
}
