import { BreakpointObserver } from '@angular/cdk/layout';
import { StepperOrientation } from '@angular/cdk/stepper';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { DependentsService } from 'src/app/_services/dependents.service';
import { NomineeService } from 'src/app/_services/nominee.service';
import { PolicyHolderService } from 'src/app/_services/policy-holder.service';
import { PolicyService } from 'src/app/_services/policy.service';

@Component({
  selector: 'app-policy-wizard',
  templateUrl: './policy-wizard.component.html',
  styleUrls: ['./policy-wizard.component.scss'],
})
export class PolicyWizardComponent implements OnInit {
  /**
   * Form groups
   */
  policyholder!: FormGroup;
  nominee!: FormGroup;
  dependents!: FormGroup;

  /**
   * age validations
   */
  minAge!: Date | string;
  maxAge!: Date | string;
  duration: string = '1';
  nomineeAge!: Date | string;
  nomineemaxAge!: Date | string;
  today!: Date | string;

  /**
   * Policy holder btn disabled
   */
  policyholderBtnmindisabled!: boolean;
  policyholderBtnmaxdisabled!: boolean;

  /**
   * Nominee btn disabled
   */
  nomineeBtnmindisabled!: boolean;
  nomineeBtnmaxdisabled!: boolean;

  /**
   * save button disabled
   */
  btnDisabled: boolean = false;

  dependentsarray = Array.apply(null, Array(5)).map((doc) => ({
    id: this.randomString(),
    dependent_name: '',
    date_of_birth: '',
    dependent_mobile_no: '',
    dependent_relationship: '',
    dependent_address: '',
  }));

  stepperOrientation!: Observable<StepperOrientation>;

  constructor(
    private _formBuilder: FormBuilder,
    breakpointObserver: BreakpointObserver,
    private policyHolderService: PolicyHolderService,
    private policy: PolicyService,
    private router: Router,
    private snack: MatSnackBar,
    private nomineeService: NomineeService,
    private dependentsService: DependentsService
  ) {
    this.stepperOrientation = breakpointObserver
      .observe('(min-width: 800px)')
      .pipe(map(({ matches }) => (matches ? 'horizontal' : 'vertical')));
  }

  ngOnInit() {
    const policyNumber = this.policyHolderService.getPolicyNumber();
    if (policyNumber)
      this.policy.getPolicy(policyNumber).subscribe(
        (resp) => {
          this.minAge = this.generateDate(resp.minAge + 1);
          this.maxAge = this.generateDate(resp.maxAge);
          this.nomineeAge = this.generateDate(19);
          this.nomineemaxAge = this.generateDate(100);
          this.today = this.generateDate(0);
          this.policyholderBtnmindisabled = this.dateDifference(
            new Date(),
            new Date(this.maxAge)
          );
          this.policyholderBtnmaxdisabled = this.dateDifference(
            new Date(),
            new Date(this.minAge)
          );

          this.nomineeBtnmaxdisabled = this.dateDifference(
            new Date(),
            new Date(this.nomineeAge)
          );
          this.nomineeBtnmindisabled = this.dateDifference(
            new Date(),
            new Date(this.nomineemaxAge)
          );
        },
        (error) => {
          console.log(error);
        }
      );
    else this.router.navigate(['/policy']);

    /**
     * Formgroups
     */
    this.policyholder = this._formBuilder.group({
      sex: ['', Validators.required],
      dateOfBirthPolicyHolder: ['', Validators.required],
      premiumDuration: ['', Validators.required],
      residence: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      pincode: ['', Validators.required],
      medical: [''],
      accidents: [''],
    });

    this.nominee = this._formBuilder.group({
      email: ['', Validators.required],
      nomineeFirstName: ['', Validators.required],
      nomineeLastName: ['', Validators.required],
      nomineemobileNo: ['', Validators.required],
      nomineeRelationship: ['', Validators.required],
      nomineeDOB: ['', Validators.required],
    });
  }

  dateDifference(a: Date, b: Date) {
    const _MS_PER_DAY = 1000 * 60 * 60 * 24;
    const utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
    const utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());

    console.log(Math.floor((utc2 - utc1) / _MS_PER_DAY));

    return Math.floor((utc2 - utc1) / _MS_PER_DAY) <= 0 ? true : false;
  }

  checkpolicyholderdatevalidation(e: any) {
    this.policyholderBtnmindisabled = this.dateDifference(
      new Date(e.target.value),
      new Date(this.minAge)
    );
    this.policyholderBtnmaxdisabled = !this.dateDifference(
      new Date(e.target.value),
      new Date(this.maxAge)
    );

    if (this.policyholderBtnmaxdisabled || this.policyholderBtnmindisabled)
      this.snack.open(`Date should lie between given age criteria, minimum date : ${this.minAge}, maximum date : ${this.maxAge}`, 'x', {
        duration: 6000,
      });
      else
      this.snack.dismiss()
  }

  checknomineeholderdatevalidation(e: any) {
    this.nomineeBtnmaxdisabled = this.dateDifference(
      new Date(e.target.value),
      new Date(this.nomineeAge)
    );

    this.nomineeBtnmindisabled = !this.dateDifference(
      new Date(e.target.value),
      new Date(this.nomineemaxAge)
    );

    if (this.nomineeBtnmaxdisabled || this.nomineeBtnmindisabled)
    this.snack.open(`Nominee age must be 18+`, 'x', {
      duration: 6000,
    });
    else
    this.snack.dismiss()
  }

  generateDate(
    year: number,
    month: number | string = new Date().getMonth(),
    date: number | string = new Date().getDate()
  ) {
    const dateObject = new Date(
      new Date().getFullYear() - year,
      Number(month),
      Number(date)
    );
    (date = dateObject.getDate()),
      (month = dateObject.getMonth() + 1), //January is 0
      (year = dateObject.getFullYear());
    if (date < 10) {
      date = '0' + date;
    }
    if (month < 10) {
      month = '0' + month;
    }
    return year + '-' + month + '-' + date;
  }

  randomString() {
    var randomChars =
      'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var result = '';
    for (var i = 0; i < 16; i++) {
      result += randomChars.charAt(
        Math.floor(Math.random() * randomChars.length)
      );
    }
    return result;
  }

  removeThisForm(id: string) {
    this.dependentsarray = this.dependentsarray.filter(
      (doc: any) => doc.id !== id
    );
  }

  removeall(){
    this.dependentsarray = []
  }

  addThisForm() {
    if (this.dependentsarray.length < 5)
      this.dependentsarray = [
        ...this.dependentsarray,
        {
          id: this.randomString(),
          dependent_name: '',
          date_of_birth: '',
          dependent_mobile_no: '',
          dependent_relationship: '',
          dependent_address: '',
        },
      ];
    else
      this.snack.open('You can add maximum 5 dependents.', 'X', {
        duration: 6000,
      });
  }

  /**
   * buy policy function
   */
  saveformdetails() {
    /**
     * Dependents array
     */
    let detailsMissing: boolean = false;
    let emptydep: [string, any][][] = [];
    this.dependentsarray.forEach((element) => {
      emptydep.push(
        Object.entries(element).filter(
          ([key, value]) =>
            (value === '' || value === null) && key === 'dependent_name'
        )
      );
    });

    emptydep.forEach((element) => {
      if (element.length > 0) {
        detailsMissing = true;
      }
    });

    const policyholderformdata = {
      ...this.policyholder.value,
    };

    const nomineeformdata = {
      ...this.nominee.value,
    };

    if (!detailsMissing) {
      this.policyHolderService.addDetails(policyholderformdata).subscribe(
        (response: any) => {
          this.nomineeService.setPolicyHolderId(response.policyholderId);

          this.nomineeService.addDetails(nomineeformdata).subscribe(
            (response: any) => {
              if (this.dependentsarray.length > 0) {
                this.dependentsService
                  .addDependents(this.dependentsarray)
                  .subscribe(
                    (resp: any) => {
                      this.payPremium();
                    },
                    (error: any) => {
                      console.log(error);
                    }
                  );
              } else {
                this.payPremium();
              }
            },
            (error: any) => {
              console.log(error);
            }
          );
        },
        (error: any) => {
          console.log(error);
        }
      );
    } else {
      this.snack.open(
        'Please fill required details of dependent and remove unused.',
        'x',
        {
          duration: 6000,
        }
      );
    }
  }

  payPremium() {
    this.dependentsService.payPremium().subscribe(
      (resp: any) => {
        if (resp.policyApproved === 'APPROVED')
          this.snack.open(
            'Your policy has been bought successfully. Kindly proceed to pay your premium.',
            'x',
            {
              duration: 6000,
            }
          );
        else
          this.snack.open(
            'Your request has been sent for further assessment.',
            'x',
            {
              duration: 6000,
            }
          );
        this.btnDisabled = !this.btnDisabled;
        this.router.navigate(['/policyList/1']);
      },
      (error: any) => {
        console.log(error);
        if (error.error.target.status === 0) {
          this.router.navigate(['/server-down']);
        }
      }
    );
  }
}
