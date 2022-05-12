import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PolicyHolderService } from 'src/app/_services/policy-holder.service';
import { PolicyService } from 'src/app/_services/policy.service';
import { UserAuthService } from 'src/app/_services/user-auth.service';
import { UserService } from 'src/app/_services/user.service';
import { Policy } from './policy';

@Component({
  selector: 'app-policy',
  templateUrl: './policy.component.html',
  styleUrls: ['./policy.component.scss'],
})
export class PolicyComponent implements OnInit {
  policies: Policy[] = [];
  policy: Policy | undefined;

  lifePolicy: Policy[] = []
  dentalPolicy:Policy[] = []
  
  displayedColumns: string[] = ['Actions', 'Id', 'Policy Name', 'Sum', 'Tenure', 'Premium'];

  constructor(
    private policyService: PolicyService,
    private userAuthService: UserAuthService,
    private route: Router,
    private userService: UserService,
    public dialog: MatDialog,
    private policyHolderService:PolicyHolderService,
  ) {}
  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  openDialog(policydetail:any) {
    const dialogRef = this.dialog.open(InfoModalComponent, {
      data : {
        ...policydetail
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  ngOnInit(): void {
    this.policyService.findAllPolicy().subscribe(
      (response: any) => {
        this.policies = response;
        this.dentalPolicy = response.filter((doc:any) => doc.policyType==='dental')
        this.lifePolicy = response.filter((doc:any) => doc.policyType!=='dental')

      },
      (error) => {
        console.log(error); 
        if (error.error.target.status === 0) {
          this.route.navigate(['/server-down']);
        }
      }
    );
  }




  // onClickBuy(policyNumber: number){
  //   this.userService.deleteUser(userId)
  //   .subscribe(responseData=> {
  //       this.deleteMsg = 'Successfully deleted';
  //       // get user records after deletion
  //       this.userService.getAllUsers().subscribe(data =>{
  //         console.log(data);
  //         this.users = data;
  //     })
  //   }, error=>{
  //       this.deleteMsg = error
  //   });
  // }

  onClickBuy(policyNumber: number) {
    this.policyHolderService.setPolicyNumber(policyNumber);
    this.route.navigate(['/policy-wizard']);
  }

  onClickBuyLogin(policyNumber: number) {
    localStorage.setItem('redirectTo', '/policy/policyNumber');

    this.route.navigate(['/sign-in']);
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
}


@Component({
  selector: 'app-info-modal',
  templateUrl: './InfoModal.component.html',
})
export class InfoModalComponent{
  constructor(@Inject(MAT_DIALOG_DATA) public data: any){}
}
