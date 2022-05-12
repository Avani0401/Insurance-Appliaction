import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';
import { environment as env } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  requestHeader = new HttpHeaders({
    'No-Auth': 'True',
  });
  enabled = false;
  constructor(
    private httpclient: HttpClient,
    private userAuthService: UserAuthService
  ) {}

  public login(loginData: any) {
    return this.httpclient.post(`${env.BASE_URL}/authenticate`, loginData, {
      headers: this.requestHeader,
    });
  }
  public signUp(signUpData: any, enabled: any) {
    if (enabled == true) {
      return this.httpclient.post(
        `${env.BASE_URL}/registerNewUnderwriter`,
        signUpData,
        { headers: this.requestHeader }
      );
    } else {
      return this.httpclient.post(
        `${env.BASE_URL}/registerNewUser`,
        signUpData,
        { headers: this.requestHeader }
      );
    }
  }

  public userdetail(){
    return this.httpclient.get(`${env.BASE_URL}/userdetail`);
  }

  public roleMatch(allowedRoles: any): boolean {
    let isMatch = false;
    const userRoles = this.userAuthService.getRoles();
    if (userRoles != null && userRoles) {
      if (userRoles === allowedRoles) {
        isMatch = true;
        return isMatch;
      } else {
        return isMatch;
      }
    }
    return isMatch;
  }
}
