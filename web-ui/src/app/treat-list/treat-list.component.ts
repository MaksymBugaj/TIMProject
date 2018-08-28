import { Component, OnInit } from '@angular/core';
import { Http } from "@angular/http";

@Component({
  selector: 'app-treat-list',
  templateUrl: './treat-list.component.html',
  styleUrls: ['./treat-list.component.css']
})
export class TreatListComponent implements OnInit {

  query: any;
  treatments: any;
  

  private treatmentUrl = 'https://tim-front2.herokuapp.com/api/treatments/';

  constructor(
    private http: Http
  ) { }

  ngOnInit() {
    this.getTreatments();
  }

  getTreatments(): any {
    return this.http.get(this.treatmentUrl).subscribe(res => {
      this.treatments = res.json();
      console.log(res);
      console.log(this.treatments);
    });
  }

}
