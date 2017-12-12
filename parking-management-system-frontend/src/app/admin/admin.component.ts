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
  allCars: any[];
  allPasses: any[];
  selectedParkingZone: any;
  headerTitle: string;

  constructor(private appService: AppService,
              private growlMessage: MessageService) {
    this.parkingZones = [];
    this.msgs = [];
  }

  ngOnInit() {
    this.listParkingZones();
    this.listOnGoingCars();
    this.listAllCars();
    this.listAllPasses();
  }

  public saveParkingZone(): void {
    this.appService.callRestPost('parkingZone/save',
      {id: this.zoneId, zoneCode: this.zoneCode, parkingCostPerHour: this.parkingCostPerHour})
      .then(response => {
        this.listParkingZones();
        this.displayDialog = false;
        this.growlMessage.add({
          severity: 'success',
          summary: 'Zone saved successfully',
          detail: `Zone saved with zone code: ${response.zoneCode}!`
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

  openEditZoneDialog(): void {
    this.headerTitle = 'Edit parking zone';
    this.zoneId = this.selectedParkingZone.id;
    this.zoneCode = this.selectedParkingZone.label;
    this.parkingCostPerHour = this.selectedParkingZone.parkingCostPerHour;
    this.displayDialog = true;
  }

  openNewZoneDialog(): void {
    this.zoneId = null;
    this.zoneCode = null;
    this.parkingCostPerHour = null;
    this.headerTitle = 'New parking zone';
    this.displayDialog = true;
  }

  closeDialog(): void {
    this.zoneId = null;
    this.zoneCode = null;
    this.parkingCostPerHour = null;

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
      this.parkingZones = response
        .map(parkingZone => ({label: parkingZone.zoneCode, id: parkingZone.id, parkingCostPerHour: parkingZone.parkingCostPerHour}));
      console.log(this.parkingZones);
    });
  }

  listAllCars(): void {
    this.appService.callRestGet('car/list')
      .then(carResponse => {
        this.allCars = carResponse;
      });
  }
  listAllPasses(): void {
    this.appService.callRestGet('parkingPass/list')
      .then(carResponse => {
        this.allPasses = carResponse;
        console.log(this.allPasses);
      });
  }
}
