import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UnderwritersService } from 'src/app/_services/underwriters.service';
import Underwiters from './Underwriters';

@Component({
  selector: 'app-underwriters-list',
  templateUrl: './underwriters-list.component.html',
  styleUrls: ['./underwriters-list.component.scss'],
})
export class UnderwritersListComponent implements OnInit {
  UnderwriterAll: Underwiters[] = [];
  UnderwriterRequests: Underwiters[] = [];
  displayedColumns: string[] = [
    'actions',
    'id',
    'username',
    'name',
    'email',
    'phone',
    'enabled',
  ];
  UnderwriterdisplayedColumns: string[] = [
    'id',
    'username',
    'name',
    'email',
    'phone',
    'enabled',
  ];

  constructor(private underwriterService: UnderwritersService, private snack:MatSnackBar, private route:Router) {}

  ngOnInit(): void {
    this.getTables('')
  }

  getTables(username:string){
    this.underwriterService.findallUnderwriters().subscribe(
      (response: any) => {
        this.UnderwriterAll = response;
      },
      (error:any) => {
        console.log(error);      
        if (error.error.target.status === 0) {
          this.route.navigate(['/server-down']);
        }
      }
    );

    if(username===''){
      this.underwriterService.findUnderwritersRequests().subscribe(
        (response: any) => {
          this.UnderwriterRequests = response;
        },
        (error:any) => {
          console.log(error);
        }
      );
    }else
    this.UnderwriterRequests = this.UnderwriterRequests.filter((doc:any) => doc.userName!==username)
  }

  activateUnderwriter(data:any){
    this.underwriterService.setUnderwriterRequests(data).subscribe(
      (response: any) => {
        if(response.enabled){
          this.snack.open(`${response.userName}'s account activated.`, 'X', {
            duration: 8000
          })
        }
        this.getTables(response.userName)
      },
      (error:any) => {
        console.log(error);
      }
    )
  }
}
