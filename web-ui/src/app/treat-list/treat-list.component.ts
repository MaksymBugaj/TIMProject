import { Component, OnInit } from '@angular/core';
import { Http } from "@angular/http";
import { BEComService } from '../_service/becom.service';
import { DatabaseService } from '../_service/database.service';
import { AuthService } from '../_service/auth.service';

@Component({
  selector: 'app-treat-list',
  templateUrl: './treat-list.component.html',
  styleUrls: ['./treat-list.component.css']
})
export class TreatListComponent implements OnInit {

  query: any;
  treatments: any;
  users: any;
  userType;
  treatmentData: any = {};

  constructor(
    private http: Http,
    private beCom: BEComService,
    private dbService: DatabaseService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    // this.beCom.getTreatments().subscribe(res => {
    //   this.treatments = res.json();
    //   console.log(res);
    //   console.log(this.treatments);
    // });

    this.treatments = this.dbService.getZabiegi();
    this.users = this.dbService.getUsers();
    this.users.subscribe(x =>
      x.forEach(element => {
        if (this.authService.authState.email === element.email) {
          this.userType = element.type;
        }
      }));
  }

  treatment(form) {
    if (form.valid) {
      this.dbService.newZabieg(form.value)
    } else {
      alert("błędny formularz");
    }
  }

}
