import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PolicyService } from 'src/app/_services/policy.service';
import { Policy } from '../policy/policy';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-policy',
  templateUrl: './add-policy.component.html',
  styleUrls: ['./add-policy.component.scss'],
})
export class AddPolicyComponent implements OnInit {
  policy: Policy = {
    policyNumber: 0,
    policyName: '',
    sumInsured: 0,
    tenure: 0,
    premium: 0,
    description: '',
    minAge: 0,
    maxAge: 0,
    duration: 0,
    policyType: ''
  };

  constructor(
    private policyService: PolicyService,
    private snackbar: MatSnackBar,
    private router: Router,
    private activatedroute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const policy_id = this.activatedroute.snapshot.paramMap.get('policy_id');
    if (policy_id) {
      this.policyService.getPolicy(policy_id).subscribe(
        (resp: any) => {
          this.policy = resp
        }
      )
    }
  }

  savePolicy(policyForm: NgForm) {
    const formobject = {
      ...policyForm.value,
      description: policyForm.value.description.split('\n').join('#'),
    };

    if (formobject.minAge >= formobject.maxAge) {
      this.snackbar.open('Min age should be smaller than Max age');
    } else {

      if (formobject.policyNumber > 0) {
        this.policyService.updatePolicy(formobject.policyNumber, formobject).subscribe((resp: any) => {

          this.snackbar.open('Policy created successfully', 'X', {
            duration: 8000
          });
          this.router.navigate(['/policy'])
        }, (error: any) => {
          console.log(error)
        })
      } else
        this.policyService.savePolicy(formobject).subscribe(
          (resp: any) => {
            if (resp.policyNumber) {
              this.snackbar.open('Policy created successfully', 'X', {
                duration: 8000
              });
              this.router.navigate(['/policy'])
            } else {
              this.snackbar.open('Unexpected error occured', 'X', {
                duration: 8000
              });
            }
          },
          (error) => {
            console.log(error);
          }
        );
    }
  }
}
