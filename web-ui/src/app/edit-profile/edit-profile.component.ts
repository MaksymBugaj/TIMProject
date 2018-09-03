import { Component, OnInit } from '@angular/core';
import { BEComService } from '../_service/becom.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  editData: any = {};

  constructor(
    private beCom: BEComService
  ) { }

  ngOnInit() {
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
