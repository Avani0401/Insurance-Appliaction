import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ClaimUserService } from 'src/app/_services/claim-user.service';
import { ClaimInfoComponent } from '../claim-user/claim-user.component';

@Component({
  selector: 'app-claim-list',
  templateUrl: './claim-list.component.html',
  styleUrls: ['./claim-list.component.scss'],
})
export class ClaimListComponent implements OnInit {
  claimList: any[] = [];
  displayHeads: String[] = [
    'Actions',
    'Id',
    'Policy',
    'Maturity Amount',
    'Request By',
    'Requested On',
    'Status',
  ];
  requestdata: any = null;

  constructor(
    private claimService: ClaimUserService,
    private dialog: MatDialog,
    private snack:MatSnackBar,
    private route:Router
  ) {}

  ngOnInit(): void {
    this.getRequests();
  }

  getRequests(){
    this.claimService.findAllClaims().subscribe(
      (resp) => {
        this.claimList = resp;
      },
      (error) => {
        if(error.error.status){
          this.claimList = []
        }
        if (error.error.target.status === 0) {
          this.route.navigate(['/server-down']);
        }
      }
    );
  }

  openDialog(claimData: any) {
    const dialogRef = this.dialog.open(ClaimInfoComponent, {
      data: {
        claimData,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  openAcceptClaim() {
    this.claimService.acceptClaim(this.requestdata).subscribe(
      (resp) => {
        this.claimList = this.claimList.map((doc:any)=>
           ( doc.claimId===resp.claimId ? {...doc, ...resp} : doc)
          )
        this.snack.open("Request accepted successfully", 'x', {
          duration:6000
        })
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openRejectClaim(formdata: NgForm) {
    const reason = formdata.value.reason;
    if (reason.length > 10)
      this.claimService.rejectClaim(this.requestdata, reason).subscribe(
        (resp) => {
          this.claimList = this.claimList.map((doc:any)=>
           ( doc.claimId===resp.claimId ? {...doc, ...resp} : doc)
          )
          this.snack.open("Request rejected successfully", 'x', {
            duration:6000
          })
        },
        (error) => {
          console.log(error);
        }
      );
  }
}
