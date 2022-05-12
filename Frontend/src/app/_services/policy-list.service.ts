import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { policyListClass } from '../pages/policy-list/policyListClass';

@Injectable({
  providedIn: 'root'
})
export class PolicyListService {

  constructor(private httpclient:HttpClient) { }
  public findAllPolicy(page:number=0){
    return this.httpclient.get<policyListClass[]>(`${environment.BASE_URL}/policyhistory/${page}`);
  }
}
