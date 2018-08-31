import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  userData: any = {
    firstName: "NameExample", lastName: "LastNameExample"
  }
  userType: number = 1;

  constructor() { }

  ngOnInit() {
  }

}
