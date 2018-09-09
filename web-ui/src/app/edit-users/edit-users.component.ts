import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_service/auth.service';
import { DatabaseService } from '../_service/database.service';

@Component({
  selector: 'app-edit-users',
  templateUrl: './edit-users.component.html',
  styleUrls: ['./edit-users.component.css']
})
export class EditUsersComponent implements OnInit {

  users

  constructor(
    private authService: AuthService,
    private dbService: DatabaseService,
  ) { }

  ngOnInit() {
    this.users = this.dbService.getUsers();
  }

  changeFlag(user) {
    this.dbService.changeFlag(user.key, user.type);
  }
}
