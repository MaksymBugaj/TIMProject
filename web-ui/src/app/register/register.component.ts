import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerData: any = {};

  constructor() { }

  ngOnInit() {
  }

  register(form)
  {
    console.log(form.value);
    
  }
}