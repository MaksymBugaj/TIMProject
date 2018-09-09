import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AngularFireObject, AngularFireDatabase } from '@angular/fire/database';
import { AngularFireAuth } from '@angular/fire/auth';
import * as firebase from 'firebase/app';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authState: any = null;
  userRef: AngularFireObject<any>; 

  constructor(
    private afAuth: AngularFireAuth,
    private db: AngularFireDatabase,
    private router: Router
  ) 
  {

    this.afAuth.authState.subscribe((auth) => {
      this.authState = auth
      if(auth)
      {
      }
    });

  }

  get authenticated$(): Observable<boolean> {
    return this.afAuth.authState.map((user) => !!user);
  }

  get authenticated(): boolean {
    return this.authState !== null;
  }

  get currentUser(): any {
    return this.authenticated ? this.authState : null;
  }

  get currentUserObservable(): any {
    return this.afAuth.authState
    
  }

  get currentUserId(): string {
    return this.authenticated ? this.authState.uid : '';
  }


  get currentUserDisplayName(): string {
    if (!this.authState) {
      return 'Guest'
    } else {
      return this.authState['email'] || 'User without a Name'
    }
  }

  emailSignUp(userInfo: any) {
    const fbAuth = firebase.auth();
    return this.afAuth.auth.setPersistence("local")
    .then(() => {
      this.afAuth.auth.createUserWithEmailAndPassword(userInfo.email, userInfo.password)
      .then((user) => {
        this.authState = user.user;
        this.updateUserFullData(userInfo);
        this.router.navigate(['']);
      })
      .catch(error => {
        alert("Email jest już używany przez inną osobę");
        console.log(error)
      });
    });
  }

  emailLogin(userInfo: any) {
    return this.afAuth.auth.setPersistence("local")
    .then(() => {this.afAuth.auth.signInWithEmailAndPassword(userInfo.email, userInfo.password)
      .then((user) => {
        this.authState = user.user;
        this.router.navigate(['']);
      })
      .catch(error => {
        alert("Użytkownik o podanym adresie E-mail nie istnieje lub podane hasło jest blędne");
        console.log(error)
      });
    });
  }

  resetPassword(email: string) {
    const fbAuth = firebase.auth();

    return fbAuth.sendPasswordResetEmail(email)
      .then(() => console.log('email sent'))
      .catch((error) => console.log(error))
  }


  signOut(): void {
    this.afAuth.auth.signOut();
    this.router.navigate(['/login'])
    alert("Wylogowanie");
  }


  private updateUserData(): void {
    const path = `users/${this.currentUserId}`; 
    const userRef: AngularFireObject<any> = this.db.object(path);

    const data = {
      email: this.authState.email,
    }

    userRef.update(data)
      .catch(error => console.log(error));

  }

  updateUserFullData(userInfo): void {
    const userID = this.db.createPushId();
    const path = `users/${userID}`;
    const userRef: AngularFireObject<any> = this.db.object(path);

    const data = {
      pesel: userInfo.PESEL,
      firstName: userInfo.firstName ,
      lastName: userInfo.lastName ,
      sex: userInfo.sex ,
      email: userInfo.email,
      password: userInfo.password,
      phone: userInfo.phone,
      type: 0,
      specialization: "Brak"
    }

    userRef.set(data)
      .catch(error => console.log(error));

  }


}