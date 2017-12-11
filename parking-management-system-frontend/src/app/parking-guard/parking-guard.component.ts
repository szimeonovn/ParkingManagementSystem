import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {Message} from "primeng/components/common/message";
import {SelectItem} from "primeng/primeng";

@Component({
  selector: 'app-parking-guard',
  templateUrl: './parking-guard.component.html',
  styleUrls: ['./parking-guard.component.css']
})
export class ParkingGuardComponent implements OnInit {
  isLoading: boolean;
  parkingZones: SelectItem[];
  msgs: Message[];

  licensePlateNumber: string;
  selectedParkingZone: number;
  displayParkingInfoDialog = false;


  constructor(private appService: AppService) {
    this.parkingZones = [];
    this.msgs = [];

  }

  ngOnInit() {
    this.listParkingZones();
  }
  listParkingZones(): void {
    this.appService.callRestGet('parkingZone/list').then(response => {
      this.isLoading = false;
      this.parkingZones = response.map(parkingZone => ({label: parkingZone.zoneCode, value: parkingZone.id}));
    });
  }
  checkParking(): void {
    this.appService.callRestPost('parking/checkParking', {
      licensePlateNumber: this.licensePlateNumber,
      parkingZoneId: this.selectedParkingZone
    })
      .then(response => {
        this.displayParkingInfoDialog = true;
        console.log(response);
        // this.parkingPrice = response.parkingCostToPay;
        // this.parkingTime = response.parkingTime;
      });
  }

}
