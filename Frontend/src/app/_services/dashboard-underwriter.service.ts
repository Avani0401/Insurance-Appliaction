import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DashboardUnderwriterService {
  
  requestHeader = new HttpHeaders(
    {
      "No-Auth":"True"
    }
  )

  constructor(private httpclient: HttpClient) {}
  
  public getPolicyHolderRequests(){
    return this.httpclient.get(`${environment.BASE_URL}/pendingUser`)
  }

  
  public approveBuyRequest(data:any){
    return this.httpclient.put(`${environment.BASE_URL}/approveBuyRequest`, data)
  }

  public rejectBuyRequest(data:any, reason:string){
    return this.httpclient.put(`${environment.BASE_URL}/rejectBuyRequest?reason=${reason}`, data)
  }


}
