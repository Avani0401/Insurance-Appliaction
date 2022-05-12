import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/_services/user-auth.service';
import { UserService } from '../../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router,
    private snack: MatSnackBar
  ) {}

  public togglepassword: boolean = true;

  inputs = {
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required]),
  };

  ngOnInit(): void {
    if (this.userAuthService.isLoggedIn()) {
      this.router.navigate(['/policy']);
    }
  }

  /**
   * Errors for form fields
   */
  getUsernameErrorMessage() {
    if (this.inputs.username.hasError('required')) {
      return 'Username field cannot be empty';
    }
    return null;
  }
  getPasswordErrorMessage() {
    if (this.inputs.password.hasError('required')) {
      return 'Password field cannot be empty';
    }
    return null;
  }

  /**
   * Login action
   */
  login() {
    const loginForm = {
      userName: this.inputs.username.value,
      userPassword: this.inputs.password.value,
    };

    const result = Object.entries(loginForm).filter(
      ([key, value]) => value == ''
    );

    if (!result.length) {
      this.userService.login(loginForm).subscribe(
        (response: any) => {
          if (response.user.enabled) {
            this.userAuthService.setUserName(response.user.userName);
            this.userAuthService.setRoles(response.user.role[0].roleName);

            this.userAuthService.setToken(response.jwtToken);
            const role = response.user.role[0].roleName;

            switch (role) {
              case 'Admin':
                location.href = '/policy';
                break;
              case 'Underwriter':
                location.href = '/underwriter';
                break;
              case 'User':
                location.href = '/policy';
            }
          } else {
            this.snack.open('Account not activated', 'x', {
              duration: 6000,
            });
          }
        },
        (error) => {
          if (error.error.message === 'Invalid credentials')
            this.snack.open('Invalid Username or Password', 'x', {
              duration: 6000,
            });
            else if (error.error.message === 'Unauthorized')
              this.snack.open('Username not found', 'x', {
                duration: 6000,
              });
          if (error.error.target.status === 0) {
            this.router.navigate(['/server-down']);
          } else console.log(error);
        }
      );
    }
  }
}
