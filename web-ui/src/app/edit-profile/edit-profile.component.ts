import { Component, OnInit } from '@angular/core';
import { BEComService } from '../_service/becom.service';
import { AuthService } from '../_service/auth.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  editData: any = {};

  constructor(
    private beCom: BEComService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.beCom.getUserByEmail(this.authService.authState.email).subscribe(res => {
      this.editData = res.json();
      console.log(this.editData);
      console.log(this.authService.authState);
    });
  }

  edit(form)
  {
    console.log(form.value);
    if (form.valid) {
      this.beCom.editUser(form.value);
    } else {
      console.log("Błędne wypełnienie");
    }
  }
}
