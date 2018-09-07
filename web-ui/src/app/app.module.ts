import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { registerLocaleData } from '@angular/common';
import localePl from '@angular/common/locales/pl';
registerLocaleData(localePl);

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '../../node_modules/@angular/forms';
import { AppRoutingModule } from './/app-routing.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { CallendarComponent } from './callendar/callendar.component';
import { DocListComponent } from './doc-list/doc-list.component';
import { TreatListComponent } from './treat-list/treat-list.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { CalendarModule } from 'angular-calendar';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap/modal/modal.module';
import { SearchPipe } from './_pipe/search.pipe';
import { CallendarDocComponent } from './callendar-doc/callendar-doc.component';
import { HttpModule } from '@angular/http';
import { AngularFireModule } from '@angular/fire';
import { AngularFireAuth } from "@angular/fire/auth";
import { AngularFireDatabase } from "@angular/fire/database";
export const firebaseConfig = {
  apiKey: "AIzaSyCbGHOGk7WaAKTUXC9Zan81cT9rwDhnMBI",
  authDomain: "timproject-290e2.firebaseapp.com",
  databaseURL: "https://timproject-290e2.firebaseio.com",
  projectId: "timproject-290e2",
  storageBucket: "timproject-290e2.appspot.com",
  messagingSenderId: "927559001233"
};

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    CallendarComponent,
    DocListComponent,
    TreatListComponent,
    EditProfileComponent,
    SearchPipe,
    CallendarDocComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NgbModule.forRoot(),
    NgbModalModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    CalendarModule.forRoot(),
    HttpModule,
    AngularFireModule.initializeApp(firebaseConfig),

  ],
  providers: [
    AngularFireAuth,
    AngularFireDatabase
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
