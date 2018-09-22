import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
  ChangeDetectorRef
} from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
  isPast,
  isFuture,
  endOfWeek,
  startOfWeek,
  startOfMonth
} from 'date-fns';
import { Subject, Observable } from 'rxjs';
import { map } from "rxjs/operators";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap/modal/modal.module';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  DAYS_OF_WEEK,
  CalendarDateFormatter,
  CalendarMonthViewDay
} from 'angular-calendar';
import { CustomDateFormatter } from './custom-date-formatter.provider';
import { Appointment } from '../_model/appointment';
import { BEComService } from '../_service/becom.service';
import 'rxjs/Rx';
import { DatabaseService } from '../_service/database.service';
import { AuthService } from '../_service/auth.service';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  blue: {
    primary: 'blue',
    secondary: '#D1E8FF'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  },
  green: {
    primary: '#1dda17',
    secondary: '#ceffcc'
  },
  cyan: {
    primary: 'cyan',
    secondary: '#D1E8FF'
  },
  black: {
    primary: '#000000',
    secondary: '#000000'
  }
};

@Component({
  selector: 'app-callendar',
  templateUrl: './callendar.component.html',
  styleUrls: ['./callendar.component.css'],
  providers: [
    {
      provide: CalendarDateFormatter,
      useClass: CustomDateFormatter,
    },
  ]
})
export class CallendarComponent {

  view: string = 'month';
  locale: string = 'pl';
  weekStartsOn: number = DAYS_OF_WEEK.MONDAY;
  weekendDays: number[] = [DAYS_OF_WEEK.SATURDAY, DAYS_OF_WEEK.SUNDAY];
  viewDate: Date = new Date();
  modalData: {
    action: string;
    event: CalendarEvent;
  };
  refresh: Subject<any> = new Subject();
  activeDayIsOpen: boolean = false;
  clickedDate: Date = new Date();

  events$: Observable<Array<CalendarEvent<{ appointment: any }>>>;
  events: any;

  treatments: any = [{}];
  choosenTreat: any;
  doctors: any = [{}];
  choosenDoctor: any;
  userEmail: String;

  modalHeader: string;
  modalBody: string;
  closeResult: string;
  modalRef;

  constructor(
    private modal: NgbModal,
    private beCom: BEComService,
    private dbService: DatabaseService,
    private authService: AuthService
  ) {
    // this.beCom.getAppointments().subscribe(res => {
    //   this.events = res.json()
    //   this.events$ = this.events
    //     .map((appointment) => this.fromAppointmentsToEvents(appointment));
    // });

    // this.beCom.getDoctors().subscribe(res => {
    //   this.doctors = res.json();
    // });

    // this.beCom.getTreatments().subscribe(res => {
    //   this.treatments = res.json();
    // });


    this.events$ = this.dbService.getAppointments()
      .map((appointments) => this.fromAppointmentsToEvents(appointments))

    this.doctors = this.dbService.getDoctors();
    this.treatments = this.dbService.getZabiegi();
    this.userEmail = this.authService.authState.email;
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;
      this.view = 'day';
    }
  }

  timeClicked(time: Date): void {
    this.clickedDate = time;
    this.view = 'month';
  }

  handleEvent(action: string, event: CalendarEvent, content, content2): void {
    this.modalData = { event, action };
    this.modalHeader = "Wizyta dnia " + event.start.toLocaleDateString() + " o godzinie " + event.meta.appointment.time + " na zabieg: " + event.meta.appointment.treatName;
    if (event.meta.appointment.flag == 0) {
      this.modalBody = "Czy chcesz się zapisać na ten termin wizyty?"
      this.openModal(content);
    } else if (event.meta.appointment.flag == 1 && event.meta.appointment.patientEmail == this.authService.currentUserDisplayName) {
      this.modalBody = "Czy chcesz odwołać wizytę?"
      this.openModal(content);
    } else if (event.meta.appointment.flag == 2 && event.meta.appointment.patientEmail != this.authService.currentUserDisplayName) {
      this.modalBody = "Termin zajęty"
      this.openModal(content2);
    } else if (event.meta.appointment.flag == 2 && event.meta.appointment.patientEmail == this.authService.currentUserDisplayName) {
      this.modalBody = "Twoja rezerwacja została potwierdzona"
      this.openModal(content2);
    } else {
      this.modalBody = "Termin zarezerwowany"
      this.openModal(content2);
    }
  }

  openModal(content) {
    this.modalRef = this.modal.open(content, { centered: true } )
  }

  manageEvent(){
    if (this.modalData.event.meta.appointment.flag == 0) {
      this.dbService.updateAppointment(this.modalData.event.meta.appointment.key, this.authService.currentUserDisplayName);
      this.modalRef.close();
    } else if (this.modalData.event.meta.appointment.flag == 1 && this.modalData.event.meta.appointment.patientEmail == this.authService.currentUserDisplayName) {
      this.dbService.updateAppointment(this.modalData.event.meta.appointment.key, "");
      this.modalRef.close();
    } else if (this.modalData.event.meta.appointment.flag == 2 && this.modalData.event.meta.appointment.patientEmail == this.authService.currentUserDisplayName) {
      this.modalRef.close();
      alert("Termin zajęty przez Ciebie")
    } else {
      this.modalRef.close();
      alert("Termin zarezerwowany")
    }
  }

  onTreatChange() {
    this.events$ = this.events$
      .map((appointments) => {
        if (this.choosenTreat == "") {
          return appointments;
        } else {
          return appointments.filter((appointment) => this.choosenTreat == appointment.meta.appointment.treatName)
        }
      });
  }

  onDoctorChange() {
    this.events$ = this.events$
      .map((appointments) => {
        if (this.choosenDoctor == "") {
          return appointments;
        } else {
          return appointments.filter((appointment) => this.choosenDoctor == appointment.meta.appointment.doctorEmail)
        }
      });
  }

  fromAppointmentsToEvents(appiontments: Appointment[]) {
    return appiontments.map((appointment: Appointment) => {
      if (appointment.flag == 0 && appointment.patientEmail == "") {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.green,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 1 && appointment.patientEmail == this.userEmail) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.cyan,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 2 && appointment.patientEmail == this.userEmail) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.blue,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 1 && appointment.patientEmail != this.userEmail) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.yellow,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 2 && appointment.patientEmail != this.userEmail) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.red,
          meta: {
            appointment
          }
        })
      } else {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.black,
          meta: {
            appointment
          }
        })
      }

    })
  }
}