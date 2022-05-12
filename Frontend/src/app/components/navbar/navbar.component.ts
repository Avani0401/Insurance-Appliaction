import { Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { UserAuthService } from 'src/app/_services/user-auth.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  public show: boolean = true;
  public login: boolean = true;

  constructor(
    private router: Router,
    private location: Location,
    private userAuthService: UserAuthService,
    private userService: UserService
  ) {
    router.events.subscribe((val: any) => {
      this.show = true;
      const Currentpath: string =
        location.path() !== '' ? location.path().split('/')[1] : '';

        if (this.userAuthService.getToken() !== null && this.tokenExpired(this.userAuthService.getToken())) this.logout();

      if (
        location.path() !== '' &&
        (Currentpath === 'sign-in' || Currentpath === 'sign-up')
      ) {
        this.show = false;
        this.login = Currentpath === 'sign-in' ? false : true;
      }
    });
  }
  
  private tokenExpired(token: string) {
    const expiry = JSON.parse(atob(token.split('.')[1])).exp;
    return Math.floor(new Date().getTime() / 1000) >= expiry;
  }

  ngOnInit(): void {}


  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  public logout() {
    this.userAuthService.clear();
    window.location.href='/'
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

  public getUserName() {
    return this.userAuthService.getUserName();
  }

  onSubmit(form: NgForm) {
    this.router.navigate(['search', form.value.search]);
  }
}
