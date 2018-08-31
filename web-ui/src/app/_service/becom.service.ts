import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class BEComService {

  headers = new Headers({ 'Content-Type': 'application/json' });
  options = new RequestOptions({ headers: this.headers });

  private createUserUrl = 'https://tim-front2.herokuapp.com/api/users/userCreated'

  private doctorUrl = 'https://tim-front2.herokuapp.com/api/users/doctors';
  private treatmentUrl = 'https://tim-front2.herokuapp.com/api/treatments/';
  private appointmentUrl = 'https://tim-front2.herokuapp.com/api/appointments/';
  private appointmentAcceptUrl = 'https://tim-front2.herokuapp.com/api/appointments/';
  private appointmentRejectUrl = 'https://tim-front2.herokuapp.com/api/appointments/';


  constructor(
    private http: Http,
    private router: Router
  ) { }


  getDoctors(): any {
    return this.http.get(this.doctorUrl);
  }

  getTreatments(): any {
    return this.http.get(this.treatmentUrl);
  }

  getAppointments(): any {
    return this.http.get(this.appointmentUrl);
  }

  rejectAppointment(key: any): any {
    return this.http.put(this.appointmentRejectUrl, key)
  }
  acceptAppointment(key: any): any {
    return this.http.put(this.appointmentAcceptUrl, key)
  }
  deleteAppointment(key: any): any {
    return this.http.delete(this.appointmentUrl, key)
  }

  createUser(user): any {
    return this.http.post(this.createUserUrl, user, this.options)
      .subscribe(
        res => {
          console.log(res);
          if (res.ok) {
            alert('Pomyślnie dodano użytkownika');
            this.router.navigate(["/home"]);
          }
        },
        err => {
          console.log(err);
        }
      );
  }

  createAppointment(event): any {
    this.http.post("https://tim-front2.herokuapp.com/api/appointments", event, this.options)
      .subscribe(
        res => {
          console.log(res);
          if (res.ok) {
            alert('Pomyślnie ustalono termin');
          }
        },
        err => {
          console.log(err);
        }
      );
  }
}
