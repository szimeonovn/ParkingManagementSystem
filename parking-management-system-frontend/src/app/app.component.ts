import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service';
import {SelectItem} from 'primeng/primeng';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {



  constructor(private appService: AppService) {

  }

  ngOnInit(): void {

  }

}
