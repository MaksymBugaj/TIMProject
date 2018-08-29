import { Component, OnInit } from '@angular/core';
import { Http } from "@angular/http";

@Component({
  selector: 'app-doc-list',
  templateUrl: './doc-list.component.html',
  styleUrls: ['./doc-list.component.css']
})
export class DocListComponent implements OnInit {

  query: any;
  doctors: any;

  private doctorUrl = 'https://tim-front2.herokuapp.com/api/users/';

  constructor(private http: Http) { }

  ngOnInit() {
    this.getDoctors();
  }

  getDoctors(): any {
    return this.http.get(this.doctorUrl).subscribe(res => {
      this.doctors = res.json();
      console.log(res);
      console.log(this.doctors);
    });
  }
}
