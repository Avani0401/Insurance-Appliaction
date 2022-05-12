import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/_services/user-auth.service';
import { UserService } from '../../_services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  public togglepassword: boolean = true;
  public event: boolean = false;
  signupform!:FormGroup

  constructor(
    private userService: UserService,
    private router: Router,
    private snack: MatSnackBar,
    private userAuthService: UserAuthService,
  ) {}

  ngOnInit(): void {
    if (this.userAuthService.isLoggedIn())
      this.router.navigate(['/policy']);
  }
  
  /**
   * Front end Error handlers
   */
  inputs = {
    email: new FormControl('', [Validators.required, Validators.email]),
    username: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(15),
      Validators.pattern('^[a-zA-Z0-9]*$')
    ]),
    firstname: new FormControl('', [Validators.required,  Validators.pattern('^[a-zA-Z ]*$')]),
    lastname: new FormControl('', [Validators.required,  Validators.pattern('^[a-zA-Z ]*$')]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
    phone: new FormControl('', [
      Validators.required,
      Validators.pattern('[6-9]{1}[0-9]{9}'),
    ]),
  };

  getEMailErrorMessage() {
    if (this.inputs.email.hasError('required')) {
      return 'Email is a required field';
    }

    return this.inputs.email.hasError('email') ? 'Not a valid email' : '';
  }

  getusernameErrorMessage() {
    if (this.inputs.username.hasError('required')) {
      return 'Username is a required field';
    }
    
    if(this.inputs.username.hasError('pattern'))
      return 'Username can only contain alphanumeric.'
      
    if (this.inputs.username.hasError('minlength'))
      return 'Username length must be more than 6';

    if(this.inputs.username.hasError('maxlength'))
      return 'Username length must not exceed 15';


    return null;
  }

  getfirstnameErrorMessage() {
    if (this.inputs.firstname.hasError('required')) {
      return 'Firstname is a required field';
    }
    if (this.inputs.firstname.hasError('pattern')) {
      return 'Firstname can only contain alphabets';
    }
    return null;
  }

  getlastnameErrorMessage() {
    if (this.inputs.lastname.hasError('required')) {
      return 'Lastname is a required field';
    }
    if (this.inputs.lastname.hasError('pattern')) {
      return 'Lastname can only contain alphabets';
    }
    return null;
  }

  getPhoneErrorMessage() {
    if (this.inputs.phone.hasError('required')) {
      return 'Phone number is a required field';
    } else if (this.inputs.phone.hasError('pattern')) {
      return 'Invalid phone number';
    }
    return null;
  }

  getPasswordErrorMessage() {
    if (this.inputs.password.hasError('required')) {
      return 'Password is a required field';
    }

    if (this.inputs.password.hasError('minlength'))
      return 'Password length must be more than 6';

    return 'null';
  }

  checkCheckBoxvalue(event: any) {
    this.event = event.target.checked;
    console.log(this.event);
  }

  formSubmit() {
    const signUpForm = {
      email: this.inputs.email.value,
      userName: this.inputs.username.value,
      phone: this.inputs.phone.value,
      userFirstName: this.inputs.firstname.value,
      userLastName: this.inputs.lastname.value,
      userPassword: this.inputs.password.value,
    };

    const result = Object.entries(signUpForm).filter(
      ([key, value]) => value == ''
    );

    if (!result.length) {
      this.userService.signUp(signUpForm, this.event).subscribe(
        (response) => {
          if (!this.event)
            this.snack.open('Account created successfully.', 'x', {
              duration: 6000,
            });
          else
            this.snack.open(
              'Account created successfully. Please wait till admin authorise you.',
              'x',
              {
                duration: 6000,
              }
            );
          this.router.navigate(['/sign-in']);
        },
        (error) => {
         
          if (error)
            this.snack.open(error.error.message, 'x', {
              duration: 6000,
            });
            if (error.error.target.status === 0) {
              this.router.navigate(['/server-down']);
            }
          else console.log(error);
        }
      );
    }
  }
}
