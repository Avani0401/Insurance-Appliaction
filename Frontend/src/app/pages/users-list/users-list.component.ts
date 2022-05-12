import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UnderwritersService } from 'src/app/_services/underwriters.service';
import Underwiters from '../underwriters-list/Underwriters';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {
  UsersList:Underwiters[]= []
  
  displayedColumns: string[] = [
    'Id',
    'Username',
    'Name',
    'Email',
    'Phone',
    'Status',
  ];

  constructor(private underwriterService:UnderwritersService, private route:Router) { }

  ngOnInit(): void {
    this.underwriterService.findallUsers().subscribe((response:any)=>{
      this.UsersList = response
    }, (error) => {
      if (error.error.target.status === 0) {
        this.route.navigate(['/server-down']);
      }
      console.log(error)
    })
  }

}
