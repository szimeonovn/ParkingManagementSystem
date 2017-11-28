import {Component, OnInit} from '@angular/core';
import {SelectItem} from "primeng/components/common/selectitem";
import {AppService} from "../app.service";
import {Message} from "primeng/components/common/message";
import {MessageService} from "primeng/components/common/messageservice";

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
  parkingPasses: SelectItem[];
  selectedParkingPass: number;
  displayPassDialog = false;
  validityTime: number;

  constructor(private appService: AppService, private growlMessage: MessageService) {
    this.isLoading = false;
    this.parkingZones = [];
    this.parkingPasses = [];
    this.msgs = [];
  }

  ngOnInit() {
    this.listParkingZones();
    this.listParkingPassType();
  }

  listParkingZones(): void {
    this.appService.callRestGet('parkingZone/list').then(response => {
      this.isLoading = false;
      this.parkingZones = response.map(parkingZone => ({label: parkingZone.zoneCode, value: parkingZone.id}));
    });
  }


  startParking(): void {
    this.appService.callRestPost('parking/startParking',
      {licensePlateNumber: this.licensePlateNumber, parkingZoneId: this.selectedParkingZone})
      .then(response => {
        this.growlMessage.add({
          severity: 'success',
          summary: 'Parking successful',
          detail: `Parking started for ${response.licensePlateNumber} at ${response.parkingStart}`
        });

      }).catch(
      error => {
        console.log(error);
        this.growlMessage.add({
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
        this.growlMessage.add({
          severity: 'success',
          summary: 'Parking paid successfully',
          detail: `Parking stopped for ${response.licensePlateNumber} at ${response.parkingEnd}`
        });
      });
  }

  listParkingPassType(): void {
    this.appService.callRestGet('parkingPassType/list')
      .then(response => {
        this.parkingPasses = response.map(parkingPass => ({label: parkingPass.name, value: parkingPass.validityTime}));
        console.log(this.parkingPasses);
        this.validityTime = response.validityTime;
      })
  }

  buyPass(): void {
    this.displayPassDialog = true;
  }

  savePass(): void {
    this.appService.callRestPost('parkingPass/buy',
      {
        licensePlateNumber: this.licensePlateNumber,
        parkingZoneId: this.selectedParkingZone,
        validityTime: this.validityTime
      }).then(response => {
      this.growlMessage.add({
        severity: 'success',
        summary: 'Pass buyed successfully',
        detail: `Pass buyed for ${response.licensePlateNumber} in ${response.selectedParkingZone}`
      });
    }).catch(
      error => {
        console.log(error);
        this.growlMessage.add({
          severity: 'error',
          summary: 'Buying pass failed',
          detail: `${error.error.text}`
        })
      }
    );
  }
}
