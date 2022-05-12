import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Policy } from '../components/policy/policy';


@Injectable({
  providedIn: 'root'
})
export class PolicyService {

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
  constructor(private httpclient:HttpClient) { }

  
  public findAllPolicy(){
    return this.httpclient.get<Policy[]>(`${environment.BASE_URL}/policy/`,{headers:this.requestHeader})
  }

  public savePolicy(data:any){
    return this.httpclient.post<Policy>(`${environment.BASE_URL}/policy/`,data, {headers:this.AuthrequestHeader})
  }

  
  public updatePolicy(policyNumber:Number, data: any) {
    return this.httpclient.put<Policy>(`${environment.BASE_URL}/policy/${policyNumber}`,data, {headers:this.AuthrequestHeader})
  }
  
  public getPolicy(policy_id: string) {
      return this.httpclient.get<Policy>(`${environment.BASE_URL}/policy/${policy_id}`, {headers : this.requestHeader})
  }
}
