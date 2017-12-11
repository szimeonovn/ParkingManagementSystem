import {Component, OnInit} from '@angular/core';
import {AppService} from "../app.service";
import {SelectItem} from "primeng/primeng";
import {Message} from "primeng/components/common/message";
import {MessageService} from "primeng/components/common/messageservice";

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
  zoneId: number;
  zoneCode: string;
  parkingCostPerHour: number;

  constructor(private appService: AppService,
              private growlMessage: MessageService) {
    this.parkingZones = [];
    this.msgs = [];
  }

  ngOnInit() {
    this.listParkingZones();
    this.listOnGoingCars();
  }

  public addParkingZone(): void {
    this.appService.callRestPost('parkingZone/save',
      {id: this.zoneId, zoneCode: this.zoneCode, parkingCostPerHour: this.parkingCostPerHour})
      .then(response => {
        this.listParkingZones();
        this.displayDialog = false;
        this.growlMessage.add({
          severity: 'success',
          summary: 'Zone added successfully',
          detail: `Zone added with zone code: ${response.zoneCode}!`
        });
      }).catch( error => {
      console.log(error);
      this.growlMessage.add({
        severity: 'error',
        summary: 'Save failed',
        detail: `${error.error.text}`
      });
    });
  }

  // toggle() {
  //   this.stacked = !this.stacked;
  // }

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
      console.log(this.parkingZones);
    });
  }
}
