import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service';
import {SelectItem} from 'primeng/primeng';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  isLoading: boolean;
  parkingZones: SelectItem[];
  selectedParkingZone: number;

  constructor(private appService: AppService) {
    this.isLoading = false;
    this.parkingZones = [];
  }

  ngOnInit(): void {
    this.appService.callRestGet('parkingZone/list')
      .then(response => {
        this.isLoading = false;
        this.parkingZones = response.map(parkingZone => ({label: parkingZone.zoneCode, value: parkingZone.id}));
      });
  }

}
