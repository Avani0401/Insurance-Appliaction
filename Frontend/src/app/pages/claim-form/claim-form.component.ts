import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ClaimUserService } from 'src/app/_services/claim-user.service';


@Component({
  selector: 'app-claim-form',
  templateUrl: './claim-form.component.html',
  styleUrls: ['./claim-form.component.scss']
})
export class ClaimFormComponent implements OnInit {

  constructor(private claimUserService:ClaimUserService, private route:Router, private snack:MatSnackBar) { }

  ngOnInit(): void {
    
  }
  addDetails(detailsForm: NgForm) {
    if(detailsForm.value.illness!=='' && detailsForm.value.hospitalName!=='')
    {this.claimUserService.claim(detailsForm.value).subscribe(
      (response: any) => {
        this.snack.open('Claim request sent.', 'x', {
          duration: 6000
        })
        this.route.navigate(['/your-claims']);
      },
      (error: any) => {
        console.log(error);
        if (error.error.target.status === 0) {
          this.route.navigate(['/server-down']);
        }
      }
    );}

  }
}
