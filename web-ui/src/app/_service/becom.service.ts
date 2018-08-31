import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class BEComService {

  private doctorUrl = 'https://tim-front2.herokuapp.com/api/users/doctors';
  private treatmentUrl = 'https://tim-front2.herokuapp.com/api/treatments/';
  private appointmentUrl = 'https://tim-front2.herokuapp.com/api/appointments/';
  private appointmentAcceptUrl = 'https://tim-front2.herokuapp.com/api/appointments/';
  private appointmentRejectUrl = 'https://tim-front2.herokuapp.com/api/appointments/';
  

  constructor(
    private http: Http
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
}
