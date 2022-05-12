import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ClaimUserService } from 'src/app/_services/claim-user.service';
import { DependentsService } from 'src/app/_services/dependents.service';
import { PolicyListService } from 'src/app/_services/policy-list.service';
import { policyListClass } from './policyListClass';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-policy-list',
  templateUrl: './policy-list.component.html',
  styleUrls: ['./policy-list.component.scss'],
})
export class PolicyListComponent implements OnInit {
  
  pageEvent:any = {
    length: 0,
    pageIndex: 0,
    pageSize: 1,
    previousPageIndex: 1
  };


  policyList: policyListClass[] = [];
  policy!: policyListClass;

  displayHeads: String[] = [
    'Actions',
    'Holder Id',
    'Policy Name',
    'Maturity date',
    'Premium',
    'Nominee',
    'Policy Status',
    'Claim Status',
    'Buy on',
  ];

  constructor(
    private policyListService: PolicyListService,
    private claimUserService: ClaimUserService,
    private route: Router,
    public dialog: MatDialog,
    private dependentsService:DependentsService,
    private snack:MatSnackBar,
    private activatedroute:ActivatedRoute
  ) {}


  ngAfterViewInit(): void {
    throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
    const page = this.activatedroute.snapshot.paramMap.get('page') ;
    this.getalldata(Number(page))
  }

  getalldata(page:Number){
    this.policyListService.findAllPolicy(Number(page)-1).subscribe(
      (response: any) => {
        this.policyList = response.content.filter((doc:any)=> 
          doc.nominee!==null
        );

        this.pageEvent = {
          length: response.totalElements,
          pageIndex: response.number,
          pageSize: response.numberOfElements,
          previousPageIndex: 1
        }

      },
      (error) => {
        if (error.error.target.status === 0) {
          this.route.navigate(['/server-down']);
        }
      }
    );
  }

  changePage(){
    const page = Number(this.pageEvent.pageIndex)+1;
    this.route.navigate([`/policyList/${page}`])
    this.getalldata(page)
  }

  openDialog(policyData: any) {
    const dialogRef = this.dialog.open(InfoPolicyComponent, {
      data: {
        policyData,
        user:'user'
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  onClickClaim(policyholderId: number, hasClaimed:boolean) {
    if(hasClaimed)
      return this.snack.open('You have already claimed for this policy', 'x', {
        duration : 6000
      })
    this.claimUserService.setClaimPolicyHolderID(policyholderId);
    this.route.navigate(['/claimForm']);
    return;
  }

  paypremium(policyholderId:number, hasClaimed:boolean){
    if (hasClaimed) {
      return this.snack.open('Policy may be expired or already claimed', 'x', {
        duration : 6000
      })
    }
    localStorage.setItem('policyHolderId', String(policyholderId))
    this.dependentsService.pay().subscribe((resp)=>{
      this.snack.open(`Premium paid for holder id ${policyholderId}`, 'X', {
        duration:6000
      })
    }, error=>{
      console.log(error);
      
    })
    return;
  }
}

@Component({
  selector: 'app-Info-policy',
  templateUrl: './InfoPolicy.component.html',
})
export class InfoPolicyComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { policyData: any, user:string }) {}
  // ngOnInit(): void {
    
  // }
}
