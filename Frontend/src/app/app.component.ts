import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from './_services/user-auth.service';
import { UserService } from './_services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  user: any = {
    userName: '',
    role: '',
  };

  _opened: boolean = false;

  constructor(
    private userAuthService: UserAuthService,
    private router: Router,
    private userService: UserService
  ) {
    this.user = {
      userName: localStorage.getItem('userName'),
      role: localStorage.getItem('role'),
    };
  }

  ngOnInit(): void {
    if (this.userAuthService.getToken() !== null && this.tokenExpired(this.userAuthService.getToken())) this.logout();
  }

  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  public logout() {
    this.userAuthService.clear();
    window.location.href = '/';
  }

  public matchAdmin() {
    return this.userService.roleMatch('Admin');
  }

  public matchUser() {
    return this.userService.roleMatch('User');
  }

  public matchUnderwriter() {
    return this.userService.roleMatch('Underwriter');
  }

  private tokenExpired(token: string) {
    const expiry = JSON.parse(atob(token.split('.')[1])).exp;
    return Math.floor(new Date().getTime() / 1000) >= expiry;
  }
}
