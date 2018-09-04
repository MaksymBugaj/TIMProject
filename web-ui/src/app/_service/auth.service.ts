import { Injectable } from '@angular/core';
import { User } from '../_model/user';
import { Http, Response } from '@angular/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private LoginUrl = 'https://tim-front2.herokuapp.com/api/users/login';

  user: User = null;

  constructor(
    private http: Http,
  ) { }  
  
  emailLogin(userInfo)
  {
    return this.http.post(this.LoginUrl, userInfo)
      .pipe(map(user => {
        if (user) {
          this.user = user.json();          
        }
        return user;
      }))
  }

  userLogout()
  {
    this.user = null;
  }

  authenticated(): Boolean {
    return this.user !== null;
  }
}
