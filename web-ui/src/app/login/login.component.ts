import { Component, OnInit } from '@angular/core';
import { Router } from '../../../node_modules/@angular/router';
import { AuthService } from '../_service/auth.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData: any = {};
  
  modalRef;
  emailRem;

  constructor(
    private auth: AuthService,
    private modal: NgbModal
  ) { }

  ngOnInit() {
    this.auth.signOut();
  }

  login(form)
  {
    if(form.valid)
    {
      this.auth.emailLogin(form.value);
    }
    else
    {
      console.log("Error");
      alert("Błędnie podane hasło lub E-mail");
    }
  }

  passwordModal(content)
  {
    this.modalRef = this.modal.open(content, { centered: true } )
  }

  resetPass() 
  {
    this.auth.resetPassword(this.emailRem);
    this.modalRef.close();
  }
}
