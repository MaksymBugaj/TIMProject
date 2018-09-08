import { Injectable } from '@angular/core';
import { AngularFireList, AngularFireDatabase, AngularFireObject } from '@angular/fire/database';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {

  appointmentsRef: AngularFireList<any>;
  userAppointmentsRef: AngularFireList<any>;
  usersRef: AngularFireList<any>;
  doctorsRef: AngularFireList<any>;
  zabiegiRef: AngularFireList<any>;

  appointments: Observable<any[]>;
  userAppointments: Observable<any[]>;
  users: Observable<any[]>;
  userstest;
  doctors: Observable<any[]>;
  zabiegi: Observable<any[]>;


  constructor(
    private db: AngularFireDatabase,
    private AuthService: AuthService
  ) {
    this.appointmentsRef = db.list('appointments');
    this.userAppointmentsRef = db.list('appointments', ref => ref.orderByChild('doctorEmail').equalTo(this.AuthService.currentUserDisplayName));
    this.usersRef = db.list('users');
    this.doctorsRef = db.list('users', ref => ref.orderByChild('type').equalTo(1));
    this.zabiegiRef = db.list('treatments');

    this.appointments = this.appointmentsRef.snapshotChanges().map(this.getFullData);
    this.userAppointments = this.userAppointmentsRef.snapshotChanges().map(this.getFullData);
    this.users = this.usersRef.snapshotChanges().map(this.getFullData);
    this.doctors = this.doctorsRef.snapshotChanges().map(this.getFullData);
    this.zabiegi = this.zabiegiRef.snapshotChanges().map(this.getFullData);

  }

  getUsers() {
    return this.users;
  }
  addUser(newName: string) {
    this.usersRef.push({ text: newName });
  }
  updateUser(key: string, flag: number) {
    if (flag==0) {
      this.usersRef.update(key, { flag: 0 });
    } else if (flag==1) {
      this.usersRef.update(key, { flag: 1 });
    } else if (flag==2) {
      this.usersRef.update(key, { flag: 2 });
    } else {
      this.usersRef.update(key, { flag: 3 });
    }
  }
  deleteUser(key: string) {
    this.usersRef.remove(key);
  }
  deleteEverythingUser() {
    this.usersRef.remove();
  }

  getDoctors() {
    return this.doctors;
  }

  getZabiegi() {
    return this.zabiegi;
  }
  updateZabieg(key: string, newText: string) {
    this.zabiegiRef.update(key, { name: newText });
  }
  deleteZabieg(key: string) {
    this.zabiegiRef.remove(key);
  }
  deleteEverythingZabieg() {
    this.zabiegiRef.remove();
  }

  getAppointments() {
    return this.appointments;
  }
  updateAppointment(key: string, newText: string) {
    if (newText == "") {
      this.appointmentsRef.update(key, { patientEmail: "", flag: 0 });
    } else {
      this.appointmentsRef.update(key, { patientEmail: newText, flag: 1 });
    }
  }
  acceptAppointment(key: string) {
    this.appointmentsRef.update(key, { flag: 2 });
  }
  deleteAppointment(key: string) {
    this.appointmentsRef.remove(key);
  }
  deleteEverythingAppointment() {
    this.appointmentsRef.remove();
  }

  newAppointment(appointmentInfo): void {

    const appointmentID = this.db.createPushId()
    const path = `appointments/${appointmentID}`;
    const appointmentRef: AngularFireObject<any> = this.db.object(path);

    const data = {
      date: appointmentInfo.date,
      time: appointmentInfo.time,
      flag: 0,
      treatName: appointmentInfo.treatName,
      doctorEmail: appointmentInfo.doctorEmail,
      patientEmail: ""
    }

    appointmentRef.set(data)
      .catch(error => console.log(error));

  }

  newZabieg(zabiegInfo) {
    const zabiegID = this.db.createPushId()
    const path = `treatments/${zabiegID}`;
    const zabiegRef: AngularFireObject<any> = this.db.object(path);

    const data = {
      name: zabiegInfo.name,
      docName: zabiegInfo.docName,
      desc: zabiegInfo.desc
    }

    zabiegRef.set(data)
      .catch(error => console.log(error));

    alert("Dodano zabieg: " + zabiegInfo.name)
  }

  getFullData(changes) {
    return changes.map(c => ({ key: c.payload.key, ...c.payload.val() }));
  }
}