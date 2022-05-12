import { Injectable } from '@angular/core';
import { environment as env } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NomineeService {

  constructor(private httpclient:HttpClient) { }
  public setPolicyHolderId(policyHolderId:number){
    localStorage.setItem("policyHolderId",String(policyHolderId));
  }
  public getPolicyHolderId(){
    return localStorage.getItem('policyHolderId');
  }
  public addDetails(detailsForm:any){
    
    
    return this.httpclient.post(`${env.BASE_URL}/registerNominee/${this.getPolicyHolderId()}`,detailsForm);
  }
}
