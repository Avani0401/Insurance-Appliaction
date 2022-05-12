import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DashboardUnderwriterService } from 'src/app/_services/dashboard-underwriter.service';
import { InfoPolicyComponent } from '../policy-list/policy-list.component';
import { policyListClass } from '../policy-list/policyListClass';

@Component({
  selector: 'app-underwriter-dashboard',
  templateUrl: './underwriter-dashboard.component.html',
  styleUrls: ['./underwriter-dashboard.component.scss'],
})
export class UnderwriterDashboardComponent implements OnInit {
  allRequests: policyListClass[] = [];
  requestdata: any;
  showmodal:boolean=false

  displayHeads: String[] = [
    'Actions',
    'Policy Name',
    'Tenure',
    'Premium',
    'Nominee',
    'Status',
    'Buy on',
  ];

  constructor(
    private dashboardUnderwriterService: DashboardUnderwriterService,
    private dialog: MatDialog,
    private snack:MatSnackBar,
    private route:Router
  ) {}

  ngOnInit(): void {
    this.getData();
  }

  getData() {
    this.dashboardUnderwriterService.getPolicyHolderRequests().subscribe(
      (resp: any) => {
        this.allRequests = resp;
      },
      (error: any) => {
        console.log(error);
        if (error.error.target.status === 0) {
          this.route.navigate(['/server-down']);
        }
      }
    );
  }

  openDialog(policyData: any) {
    const dialogRef = this.dialog.open(InfoPolicyComponent, {
      data: {
        policyData,
        user:'underwriter'
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  AcceptDialog(policyData: any) {
    this.dashboardUnderwriterService.approveBuyRequest(policyData).subscribe(
      (resp) => {
        this.getData();
        this.snack.open("Policy request approved", 'x', {
          duration: 6000
        })
      },
      (error) => {
        console.log(error);
      }
    );
  }

  RejectDialog(formdata: NgForm) {
    const reason: string = formdata.value.reason;
    if (reason.length > 10)
      this.dashboardUnderwriterService
        .rejectBuyRequest(this.requestdata, reason)
        .subscribe(
          (resp) => {
            this.getData();
            this.snack.open("Policy request rejected", 'x', {
              duration: 6000
            })
          },
          (error) => {
            console.log(error);
          }
        );
  }
}
