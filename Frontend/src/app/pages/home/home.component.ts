import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PolicyHolderService } from 'src/app/_services/policy-holder.service';
import { PolicyService } from 'src/app/_services/policy.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  policies: any[] = [];
  dentalPolicy: any[] = [];
  lifePolicy: any[] = [];

  constructor(private policyService:PolicyService, private policyHolderService:PolicyHolderService, private route: Router, private userService:UserService, private userAuthService:UserAuthService ) { }

  ngOnInit(): void {
    this.policyService.findAllPolicy().subscribe(
      (response: any) => {
        this.policies = response;
        this.dentalPolicy = response.filter((doc:any) => doc.policyType==='dental')
        this.lifePolicy = response.filter((doc:any) => doc.policyType!=='dental')
      },
      (error) => {
        
        if (error.error.target.status === 0) {
          this.route.navigate(['/server-down']);
        }
      }
    );
  }

  matchUser(){

    return this.userService.roleMatch('User');
  }

  onClickBuy(policyNumber:number){
    this.policyHolderService.setPolicyNumber(policyNumber);
    this.route.navigate(['/policy-wizard']);
  }
  
  onClickBuyLogin(){
    localStorage.setItem('redirectTo', '/policy/policyNumber');

    this.route.navigate(['/sign-in']);
  }

  isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

}
