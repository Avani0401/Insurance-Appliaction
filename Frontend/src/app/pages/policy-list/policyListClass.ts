import { Policy } from "../../components/policy/policy"
import { NomineeClass } from "./NomineeClass";

export class policyListClass{
    policyholderId:number;
    maturityAmount:number;
    medical:string;
    buyOn:string;
    maturityDate:string;
    nominee:NomineeClass;
    policy:Policy;
    policyApproved:string;
    policyHolderPremium:number;
    hasClaimed:boolean;
  
    constructor(policyholderId:number,maturityAmount:number,medical:string,buyOn:string,maturityDate:string,
        nominee:NomineeClass,policy:Policy,policyApproved:string, policyHolderPremium:number, hasClaimed:boolean
        ){
        this.policyholderId=policyholderId;
        this.maturityAmount=maturityAmount;
        this.medical=medical;
        this.buyOn=buyOn;
        this.maturityDate=maturityDate;
        this.nominee=nominee;
        this.policy=policy;
        this.policyApproved=policyApproved;
        this.policyHolderPremium=policyHolderPremium;
        this.hasClaimed = hasClaimed;
        
    }
}