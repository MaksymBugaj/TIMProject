import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';
import { AuthService } from '../_service/auth.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData: any = {};

  constructor(
    private router: Router,
    private auth: AuthService
  ) { }

  ngOnInit() {
    this.auth.signOut();
  }

  login(form)
  {
    console.log(form.value);
    
    if(form.valid)
    {
      this.auth.emailLogin(form.value);
    }
    else
    {
      console.log("Error");
      alert("Błędnie podane hasło lub E-mail");
    }
  }
}
