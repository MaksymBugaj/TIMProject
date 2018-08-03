import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-treat-list',
  templateUrl: './treat-list.component.html',
  styleUrls: ['./treat-list.component.css']
})
export class TreatListComponent implements OnInit {

  treatList: any = [
    {name: "Sprawdzenie jakości widzenia", docName:"Marcin Borowski", desc:"KRÓTKI OPIS ZABIEGU 1"},
    {name: "Przegląd zębów", docName:"Maksym Bugaj", desc:"KRÓTKI OPIS ZABIEGU 2"},
    {name: "Wizyta ogólna", docName:"Mariusz Byler", desc:"KRÓTKI OPIS ZABIEGU 3"}
  ]

  constructor() { }

  ngOnInit() {
  }

}
