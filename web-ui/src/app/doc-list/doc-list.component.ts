import { Component, OnInit } from '@angular/core';
import { Http } from "@angular/http";
import { BEComService } from '../_service/becom.service';

@Component({
  selector: 'app-doc-list',
  templateUrl: './doc-list.component.html',
  styleUrls: ['./doc-list.component.css']
})
export class DocListComponent implements OnInit {

  query: any;
  doctors: any;


  constructor(
    private http: Http,
    private beCom: BEComService  
  ) { }

  ngOnInit() {
    this.beCom.getDoctors().subscribe(res => {
      this.doctors = res.json();
      console.log(res);
      console.log(this.doctors);
    });
  }
}
