import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-doc-list',
  templateUrl: './doc-list.component.html',
  styleUrls: ['./doc-list.component.css']
})
export class DocListComponent implements OnInit {

  query: any;

  docList: any = [
    {firstName: "Marcin", lastName: "Borowski", special: "Okulista", desc:"KRÓTKI OPIS LEKARZA 1"},
    {firstName: "Maksym", lastName: "Bugaj", special: "Dentysta", desc:"KRÓTKI OPIS LEKARZA 2"},
    {firstName: "Mariusz", lastName: "Byler", special: "Ogólny", desc:"KRÓTKI OPIS LEKARZA 3"}
  ]

  filteredDocList: any;

  constructor() { }

  ngOnInit() {
  }

}
