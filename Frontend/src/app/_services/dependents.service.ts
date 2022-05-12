import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DependentsService {
  
  requestHeader = new HttpHeaders(
    {
      "No-Auth":"True"
    }
  )

  constructor(private httpclient: HttpClient) {}
  
  public getPolicyHolderId(){
    return localStorage.getItem('policyHolderId');
  }

  public addDependents(data:any){
    return this.httpclient.post(`${environment.BASE_URL}/${this.getPolicyHolderId()}/addDependents`, data)
  }

  public payPremium(){
    return this.httpclient.post(`${environment.BASE_URL}/${this.getPolicyHolderId()}/payPremium`, {})
  }
  
  public pay(){
    return this.httpclient.post(`${environment.BASE_URL}/${this.getPolicyHolderId()}/pay`, {})
  }
  
}
