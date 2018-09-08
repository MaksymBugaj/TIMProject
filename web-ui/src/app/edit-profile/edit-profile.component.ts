import { Component, OnInit } from '@angular/core';
import { BEComService } from '../_service/becom.service';
import { AuthService } from '../_service/auth.service';
import { DatabaseService } from '../_service/database.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  editData: any = {};
  users;

  constructor(
    private beCom: BEComService,
    private authService: AuthService,
    private dbService: DatabaseService,
  ) { }

  ngOnInit() {
    // this.beCom.getUserByEmail(this.authService.authState.email).subscribe(res => {
    //   this.editData = res.json();
    //   console.log(this.editData);
    //   console.log(this.authService.authState);
    // });
    this.users = this.dbService.getUsers();
    this.users.subscribe(x => x.forEach(element => {      
      if(this.authService.authState.email == element.email)
      {
        this.editData=element
      }
    }));
  }

  edit(form)
  {
    console.log(form.value);
    if (form.valid) {
      this.dbService.updateUser(this.editData.key ,form.value);
    } else {
      console.log("Błędne wypełnienie");
    }
  }
}
