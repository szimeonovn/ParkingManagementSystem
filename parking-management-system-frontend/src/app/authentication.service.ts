import {Injectable} from '@angular/core';
import {TOKEN_AUTH_PASSWORD, TOKEN_AUTH_USERNAME} from './auth.constant';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AppService} from './app.service';

@Injectable()
export class AuthenticationService {

  static AUTH_TOKEN = '/oauth/token';

  constructor(private httpClient: HttpClient,
              private appService: AppService) {
  }

  login(username: string, password: string): Promise<void> {
    const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD)
    });

    return new Promise((resolve, reject) => {
      this.httpClient.post(AppService.SIMPLE_BASE_URL + AuthenticationService.AUTH_TOKEN, body, {headers: headers})
        .toPromise().then((res: any) => {
        console.log(res);
        if (res.access_token) {
          this.appService.token = res.access_token;
          resolve();
        }
      })
        .catch(reason => {
          console.log(reason);
          reject(reason);
        });
    });
  }

}
