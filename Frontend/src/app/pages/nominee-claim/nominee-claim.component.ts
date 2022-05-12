import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ClaimUserService } from 'src/app/_services/claim-user.service';
import { ClaimForm } from './nomineeClaim';

@Component({
  selector: 'app-nominee-claim',
  templateUrl: './nominee-claim.component.html',
  styleUrls: ['./nominee-claim.component.scss']
})
export class NomineeClaimComponent implements OnInit {

  claimForm!: ClaimForm;
  constructor(private claimUserService:ClaimUserService, private route:Router,  private snack:MatSnackBar) { }

  ngOnInit(): void {
  }
  addDetails(detailsForm: NgForm) {

    this.claimForm = detailsForm.value;
    
    const result = Object.entries(this.claimForm).filter(
      ([key, value]) => value == ''
    );
    if(result.length===0 && detailsForm.value.nomineeId && detailsForm.value.policyHolderId)
    this.claimUserService.nomineeClaim(this.claimForm,detailsForm.value.nomineeId,detailsForm.value.policyHolderId).subscribe(
      (response: any) => {
        this.snack.open(`Thank you, Your request has been submitted.`, 'x', {
          duration: 6000
        })
        this.route.navigate(['/']);
      },
      (error: any) => {
        if (error.status===400) {
          this.snack.open(error.error.message, 'x', {
            duration: 6000
          })
        }
        else if (error.status===406) {
          this.snack.open(error.error.message, 'x', {
            duration: 6000
          })
        }
        else if (error.error.target.status === 0)
        this.route.navigate(['/server-down']);
        else{
          console.log(error);
        }
      }
    );
    else if(detailsForm.value.nomineeId===0 || detailsForm.value.policyHolderId===0){
      this.snack.open('Policy holder id and nominee id cannot be zero or negative.', 'x', {
        duration: 6000
      })
    }
  }

}
