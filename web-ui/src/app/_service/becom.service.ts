import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class BEComService {

  private doctorUrl = 'https://tim-front2.herokuapp.com/api/users/doctors';
  private treatmentUrl = 'https://tim-front2.herokuapp.com/api/treatments/';
  private appointmentUrl = 'https://tim-front2.herokuapp.com/api/appointments/';

  constructor(
    private http: Http
  ) { }


  getDoctors(): any {
    return this.http.get(this.doctorUrl).subscribe(res => {
      console.log(res);
      console.log(res.json());
      return res.json();
    });
  }

  getTreatments(): any {
    return this.http.get(this.treatmentUrl).subscribe(res => {
      console.log(res);
      console.log(res.json());
      return res.json();
    });
  }

  getAppointments(): any {
    return this.http.get(this.appointmentUrl).subscribe(res => {
      console.log(res);
      console.log(res.json());
      return res.json();
    });
  }

}
