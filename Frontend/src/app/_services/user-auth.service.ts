import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserAuthService {
  constructor() {}

  public setUserName(userName: string) {
    localStorage.setItem('userName', userName);
  }
  public getUserName() {
    return localStorage.getItem('userName');
  }

  public setRoles(role: any) {
    localStorage.setItem('role', role);
  }

  public getRoles(): [] {
    let role: any = localStorage.getItem('role');

    return role;
  }
  public setToken(jwtToken: any) {
    localStorage.setItem('jwtToken', jwtToken);
    return true;
  }

  public getToken(): any {
    return localStorage.getItem('jwtToken');
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    let token = localStorage.getItem('jwtToken');
    if (
      token == undefined ||
      token == '' ||
      token == null ||
      !this.getRoles()
    ) {
      return false;
    } else {
      return true;
    }
  }
}
