import {Component} from '@angular/core';
import {Http} from '@angular/http';
import {AppService} from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private appService: AppService) {
  }

  test(): void {
    const body = {id: 1, name: 'testtt'};
    this.appService.callRestPost('test/save', body)
      .then(response => {
        console.log(response);
      });
  }
}
