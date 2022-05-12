import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ClaimUserService } from 'src/app/_services/claim-user.service';

@Component({
  selector: 'app-claim-user',
  templateUrl: './claim-user.component.html',
  styleUrls: ['./claim-user.component.scss']
})
export class ClaimUserComponent implements OnInit {
  claimList:any = [];
  displayHeads:String[] = ['actions', 'Id', 'Policy', 'maturityAmount', 'Request By', 'Requested On', 'Status']

  constructor(private claimService:ClaimUserService, private dialog:MatDialog, private route:Router) { }

  ngOnInit(): void {
    this.claimService.getUserCLaims().subscribe((resp)=>{
      this.claimList = resp
    }, error=>{
      console.log(error);
      if (error.error.target.status === 0) {
        this.route.navigate(['/server-down']);
      }
    })
  }

  openDialog(claimData:any){
    const dialogRef = this.dialog.open(ClaimInfoComponent, {
      data: {
        claimData,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }

}

@Component({
  selector: 'app-claim-info',
  templateUrl: './ClaimInfo.component.html',
})
export class ClaimInfoComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { claimData: any }) {console.log(this.data)}
}