import {Component} from '@angular/core';
import {AuthenticationService} from '../authentication.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MessageService} from 'primeng/components/common/messageservice';
import {Message} from 'primeng/primeng';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private redirectUrl: string;

  private username: string;
  private password: string;

  msgs: Message[];

  constructor(private authenticationService: AuthenticationService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService) {
    this.msgs = [];
    this.redirectUrl = this.activatedRoute.snapshot.queryParams['redirectTo'];
  }

  login(): void {
    this.authenticationService.login(this.username, this.password)
      .then(() => {
        this.router.navigate([this.redirectUrl]);
      }).catch(reason => {
      this.messageService.add({severity: 'error', summary: 'Login failed', detail: reason.error.error_description});
    });
  }

}
