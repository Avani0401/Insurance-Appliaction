export class ClaimForm{
    illness:string;
    hospitalName:string;
    constructor(illness:string,
        hospitalName:string){
        this.illness=illness;
        this.hospitalName=hospitalName;
    }
}