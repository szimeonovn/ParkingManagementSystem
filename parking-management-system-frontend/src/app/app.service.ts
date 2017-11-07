import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class AppService {

  private readonly BASE_URL = 'http://localhost:8080/rest/';

  constructor(private http: Http) {
  }

  public callRestPost(url: string, requestBody: any): Promise<any> {
    return new Promise<any>(resolve => {
      this.http.post(`${this.BASE_URL}${url}`, requestBody)
        .toPromise().then(response => {
        resolve(response.json());
      });
    });
  }

}
