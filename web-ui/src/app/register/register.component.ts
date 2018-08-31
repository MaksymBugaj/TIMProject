import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';
import { BEComService } from '../_service/becom.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerData: any = {};

  constructor(
    private router: Router,
    private beCom: BEComService
  ) { }

  ngOnInit() {
  }

  register(form)
  {
    console.log(form.value);
    this.beCom.createUser(form.value)
  }
}
