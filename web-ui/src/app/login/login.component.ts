import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData: any = {};

  constructor(private router: Router) { }

  ngOnInit() {
  }

  login(form)
  {
    console.log(form.value);
    this.router.navigate(['home']);
  }
}
