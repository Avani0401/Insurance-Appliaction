export class Claim{
    issued_amount:number;
    claimId:number;

    issued_date:Date;
    status:string;


    constructor(issued_amount:number,issued_date:Date,status:string, claimId:number){
        this.issued_amount=issued_amount;
        this.issued_date=issued_date;
        this.status=status;
        this.claimId=claimId;
    }
}