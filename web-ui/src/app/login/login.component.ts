import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';
import { AuthService } from '../_service/auth.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData: any = {};

  constructor(
    private router: Router,
    private auth: AuthService
  ) { }

  ngOnInit() {
    this.auth.userLogout();
  }

  login(form)
  {
    console.log(form.value);
    if (form.valid) {
      this.auth.emailLogin(form.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['home'])
          console.log(this.auth.user);
        },
        error => {
          alert("Błąd logowania");
          console.log(error);
        }
      )      
    } else {
      alert("Błąd logowania, uzupełnij dane poprawnie");
    }
  }
}
