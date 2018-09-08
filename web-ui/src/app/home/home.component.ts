import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_service/auth.service';
import { BEComService } from '../_service/becom.service';
import { DatabaseService } from '../_service/database.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  users: any;
  user: any = [
    {firstName: "test", lastName: "test"}
  ] ;
  userType: number;


  constructor(
    private authService: AuthService,
    private beCom: BEComService,
    private dbService: DatabaseService
  ) { }

  ngOnInit() {
    // this.beCom.getUserByEmail(this.authService.authState.email).subscribe(res => {
    //   this.user = res.json();
    //   this.userType = this.user.type;
    //   console.log(this.user);
    //   console.log(this.authService.authState);
    // });

    // this.beCom.getUsers().subscribe (res => {
    //   this.user = res.json();
    //   this.user.forEach(element => {
    //     if (this.authService.authState.email == element.email) {
    //       this.userType = element.type
    //     }
    //   });
    // })

    this.users = this.dbService.getUsers();
    this.users.subscribe(x =>
      x.forEach(element => {
        if (this.authService.authState.email === element.email) {
          this.userType = element.type;
          this.user = element
        }
      }));
  }

  logout() {
    alert("Zostałeś wylogowany");
    this.authService.signOut();
  }

}
