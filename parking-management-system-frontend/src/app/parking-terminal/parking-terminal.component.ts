import {Component, OnInit} from '@angular/core';
import {SelectItem} from "primeng/components/common/selectitem";
import {AppService} from "../app.service";
import {Message} from "primeng/components/common/message";

@Component({
  selector: 'app-parking-terminal',
  templateUrl: './parking-terminal.component.html',
  styleUrls: ['./parking-terminal.component.css']
})
export class ParkingTerminalComponent implements OnInit {
  isLoading: boolean;
  parkingZones: SelectItem[];
  selectedParkingZone: number;
  licensePlateNumber: string;
  msgs: Message[];
  displayParkingInfoDialog = false;
  parkingPrice: number;
  parkingTime: number;

  constructor(private appService: AppService) {
    this.isLoading = false;
    this.parkingZones = [];
    this.msgs = [];
  }

  ngOnInit() {

    this.appService.callRestGet('parkingZone/list').then(response => {
      this.isLoading = false;
      this.parkingZones = response.map(parkingZone => ({label: parkingZone.zoneCode, value: parkingZone.id}));
    });
  }

  startParking(): void {
    this.appService.callRestPost('parking/startParking',
      {licensePlateNumber: this.licensePlateNumber, parkingZoneId: this.selectedParkingZone})
      .then(response => {
        this.msgs.push({
          severity: 'success',
          summary: 'Parking successful',
          detail: `Parking started for ${response.licensePlateNumber} at ${response.parkingStart}`
        });

      }).catch(
      error => {
        console.log(error);
        this.msgs.push({
          severity: 'error',
          summary: 'Parking failed',
          detail: `${error.error.text}`
        })
      }
    );
  }

  checkParking(): void {
    this.appService.callRestGet(`parking/checkParking/${this.licensePlateNumber}`)
      .then(response => {
        this.displayParkingInfoDialog = true;
        console.log(response);
        this.parkingPrice = response.parkingCostToPay;
        this.parkingTime = response.parkingTime;
      })
  }

  stopParking(): void {
    this.appService.callRestPost(`parking/stopParking/${this.licensePlateNumber}`)
      .then(response => {
        this.displayParkingInfoDialog = false;
        this.msgs.push({
          severity: 'success',
          summary: 'Parking paid successfully',
          detail: `Parking stopped for ${response.licensePlateNumber} at ${response.parkingEnd}`
        });
      });
  }
}
