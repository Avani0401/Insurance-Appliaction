export class NomineeClass {
    nomineeFirstName: string;
    nomineeLastName: string;
    nomineeDOB: Date;
    nomineemobileNo: number;
    nomineeRelationship: string;
    email: string;
  
    constructor(
      nomineeFirstName: string,
      nomineeLastName: string,
      nomineeDOB: Date,
      nomineemobileNo: number,
      nomineeRelationship: string,
      email: string
    ) {
      this.nomineeFirstName = nomineeFirstName;
      this.nomineeLastName = nomineeLastName;
      this.nomineeDOB = nomineeDOB;
      this.nomineemobileNo = nomineemobileNo;
      this.nomineeRelationship = nomineeRelationship;
      this.email = email;
    }
  }
  