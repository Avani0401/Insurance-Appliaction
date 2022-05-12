import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private userAuthService: UserAuthService,
    private router: Router,
    private userService: UserService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (this.userAuthService.getToken !== null) {
      if (!this.tokenExpired(this.userAuthService.getToken())) {
        const role = route.data['roles'] as Array<string>;
       
       
        if (role && typeof role === 'string') {
          const match = this.userService.roleMatch(role);
          if (match) {
            return true;
          } else {
            this.router.navigate(['/page-not-found']);
            return false;
          }
        }else{
          let result = false;
          role.forEach(element => {
            const match = this.userService.roleMatch(element);
            if (match) {
              result = true;
            } 
          });
          if (result) {
            return true;
          } else {
            this.router.navigate(['/page-not-found']);
            return false;
          }

        }
      }
    }
    this.router.navigate(['/sign-in']);
    return false;
  }

  private tokenExpired(token: string) {
    const expiry = JSON.parse(atob(token.split('.')[1])).exp;
    return Math.floor(new Date().getTime() / 1000) >= expiry;
  }
}
