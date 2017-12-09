import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class AppService {

  // private static readonly BASE_URL = 'http://localhost:8080/rest/';
  // private static readonly BASE_URL = 'http://atlantissoftwareinc.ddns.net:' +
  //   '8080/rest/';
  // private static readonly BASE_URL = 'http://192.168.33.25:' +
  //   '8080/rest/'

  private static readonly BASE_URL = 'http://172.20.10.2:' +
    '8080/rest/';

  constructor(private http: HttpClient) {
  }

  public callRestPost(url: string, requestBody?: any): Promise<any> {
    console.log(`callRestPost, url: ${AppService.BASE_URL}` + url);
    return new Promise<any>((resolve, reject) => {
      this.http.post(`${AppService.BASE_URL}${url}`, requestBody)
        .toPromise()
        .then(response => {
          console.log(JSON.stringify(response, null, 2));
          resolve(response);
        }).catch(reason => {
        console.error(reason.error.text);
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

}
