import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment as env } from '../../environments/environment';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class PolicyHolderService {

  constructor(private httpclient:HttpClient) { }
  
  public setPolicyNumber(policyNumber: any) {
    localStorage.setItem('policyNumber', policyNumber);
  }
  public getPolicyNumber(){
    return String(localStorage.getItem('policyNumber'));
  }

  public addDetails(detailsForm:any){
    const a=this.getPolicyNumber();
    return this.httpclient.post(`${env.BASE_URL}/buyPolicy/${this.getPolicyNumber()}`,detailsForm);
  }
}
