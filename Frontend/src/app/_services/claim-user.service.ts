import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Claim } from '../pages/claim-list/claim-list';

@Injectable({
  providedIn: 'root',
})
export class ClaimUserService {
  
  requestHeader = new HttpHeaders(
    {
      "No-Auth":"True"
    }
  )
  AuthrequestHeader = new HttpHeaders(
    {
      "No-Auth":"False"
    }
  )

  constructor(private httpclient: HttpClient) {}


  public setClaimPolicyHolderID(policyHolderId: any) {
    localStorage.setItem('claimPolicyHolderId', policyHolderId);
  }

  public getClaimPolicyHolderId() {
    return localStorage.getItem('claimPolicyHolderId');
  }
  
  public findAllClaims() {
    return this.httpclient.get<Claim[]>(`${environment.BASE_URL}/getAdminClaimRequests`,{headers:this.AuthrequestHeader})
  }
  public claim(detailsForm: any) {
    return this.httpclient.post<Claim>(
      `${environment.BASE_URL}/${this.getClaimPolicyHolderId()}/claim`,
      detailsForm
    );
  }

  public nomineeClaim(detailsForm: any,nomineeId:number,policyHolderId:number) {
    return this.httpclient.post<Claim>(
      `${environment.BASE_URL}/claimbyNominee/${policyHolderId}/${nomineeId}`,detailsForm,{headers:this.requestHeader})
    }

  public getUserCLaims(){
    return this.httpclient.get<Claim>(
      `${environment.BASE_URL}/getUserClaimRequests`
    )
  }

  public acceptClaim(data:any){
    return this.httpclient.put<Claim>(
      `${environment.BASE_URL}/approveClaimRequest`, data
    )
  }

  public rejectClaim(data:any, reason:string){
    return this.httpclient.put<Claim>(
      `${environment.BASE_URL}/rejectClaimRequest?reason=${reason}`, data
    )
  }
}
