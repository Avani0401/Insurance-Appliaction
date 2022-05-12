export class Policy{
    policyNumber: number;
    policyName: string;
    sumInsured: number;
    tenure: number;
    premium: number;
    description: string;
    minAge: number;
    maxAge: number;
    duration: number;
    policyType:string;


constructor(policyNumber: number,policyName:string,sumInsured: number,
    tenure: number,
    premium: number,
    description: string,
    minAge: number,
    maxAge: number,
    duration: number,policyType:string){
    this.policyNumber=policyNumber;
    this.policyName=policyName;
    this.sumInsured=sumInsured;
    this.tenure=tenure;
    this.policyType=policyType;

    this.premium=premium;
    this.description=description;
    this.minAge=minAge;
    this.maxAge=maxAge;
    this.duration=duration;

}
}