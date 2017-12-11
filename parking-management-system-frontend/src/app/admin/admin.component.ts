import {Component, OnInit} from '@angular/core';
import {AppService} from "../app.service";
import {SelectItem} from "primeng/primeng";
import {Message} from "primeng/components/common/message";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  onGoingCars: any[];
  stacked: boolean;
  displayDialog: boolean;
  isLoading: boolean;
  parkingZones: SelectItem[];
  msgs: Message[];


  constructor(private appService: AppService) {
    this.parkingZones = [];
    this.msgs = [];


  }

  ngOnInit() {
    this.listParkingZones();
    this.listOnGoingCars();
  }

  public addParkingZone(): void {
    this.appService.callRestPost('parkingZone/save');
  }

  toggle() {
    this.stacked = !this.stacked;
  }

  openNewZoneDialog(): void {
    this.displayDialog = true;
  }

  closeDialog(): void {
    this.displayDialog = false;
  }

  public listOnGoingCars(): void {
    this.appService.callRestGet('parking/listOnGoing')
      .then(onGoingCarResponse => {
        this.onGoingCars = onGoingCarResponse;
        console.log(this.onGoingCars);
      });
  }

  listParkingZones(): void {
    this.appService.callRestGet('parkingZone/list').then(response => {
      this.isLoading = false;
      this.parkingZones = response.map(parkingZone => ({label: parkingZone.zoneCode, value: parkingZone.id}));
    });
  }
}
