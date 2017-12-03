import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {isNullOrUndefined} from 'util';

@Injectable()
export class AppService {

  public static SIMPLE_BASE_URL;

  private static readonly BASE_URL = 'http://localhost:8080/rest/';
  // private static readonly BASE_URL = 'http://atlantissoftwareinc.ddns.net8080/rest/';
  // private static readonly BASE_URL = 'http://192.168.33.25:8080/rest/';

  token: string;

  constructor(private http: HttpClient) {
    AppService.SIMPLE_BASE_URL = AppService.BASE_URL.split(/rest/)[0];
  }

  public callRestPost(url: string, requestBody?: any): Promise<any> {
    console.log(`callRestPost, url: ${AppService.BASE_URL}` + url);
    return new Promise<any>((resolve, reject) => {
      this.http.post(`${AppService.BASE_URL}${url}`, requestBody, {headers: this.getHeaders()})
        .toPromise()
        .then(response => {
          console.log(JSON.stringify(response, null, 2));
          resolve(response);
        }).catch(reason => {
        console.error(reason);
        reject(reason);
      });
    });
  }

  public callRestGet(url: string): Promise<any> {
    console.log(`callRestGet, url: ${AppService.BASE_URL}` + url);
    return new Promise<any>((resolve, reject) => {
      this.http.get(`${AppService.BASE_URL}${url}`)
        .toPromise().then(response => {
        resolve(response);
      }).catch(reason => {
        console.error(reason);
        reject(reason);
      });
    });
  }

  private getHeaders(): HttpHeaders {
    if (!isNullOrUndefined(this.token)) {
      return new HttpHeaders({'Authorization': 'Bearer ' + this.token});
    } else {
      return new HttpHeaders();
    }
  }

}
