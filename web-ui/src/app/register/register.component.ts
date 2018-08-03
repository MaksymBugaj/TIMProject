import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerData: any = {};

  constructor(private router: Router) { }

  ngOnInit() {
  }

  register(form)
  {
    console.log(form.value);
    this.router.navigate(["/home"]);
  }
}
