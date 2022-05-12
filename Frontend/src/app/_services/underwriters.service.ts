import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import Underwiters from '../pages/underwriters-list/Underwriters';

@Injectable({
  providedIn: 'root'
})
export class UnderwritersService {

  AuthrequestHeader = new HttpHeaders({
    'No-Auth':"False"
  })
  constructor(private httpClient:HttpClient) {}

  public findallUsers(){
    return this.httpClient.get<Underwiters>(`${environment.BASE_URL}/alluserlist`, {
      headers: this.AuthrequestHeader
    })
  }
  public findUnderwritersRequests(){
    return this.httpClient.get<Underwiters>(`${environment.BASE_URL}/getUnderwriterRequests`, {
      headers: this.AuthrequestHeader
    })
  }
  public findallUnderwriters(){
    return this.httpClient.get<Underwiters>(`${environment.BASE_URL}/allUnderwriterlist`, {
      headers: this.AuthrequestHeader
    })
  }
  
  public setUnderwriterRequests(data:any) {
    return this.httpClient.post<Underwiters>(`${environment.BASE_URL}/approveUnderwriterRequests`, data, {
      headers: this.AuthrequestHeader
    })
  }

}
