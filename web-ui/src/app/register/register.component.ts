import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';
import { BEComService } from '../_service/becom.service';
import { AuthService } from '../_service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerData: any = {};

  constructor(
    private router: Router,
    private beCom: BEComService,
    private authService: AuthService,
  ) { }

  ngOnInit() {
  }

  register(form)
  {
    if (form.valid)
    {
      this.authService.emailSignUp(form.value);
    }
    else
    {
      alert("Błędnie uzupełniony formularz")
    } 
  }
}
