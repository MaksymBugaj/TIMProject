import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef
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
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap/modal/modal.module';
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
import { RequestOptions, Headers, Http } from '@angular/http';
import { DatabaseService } from '../_service/database.service';
import { AuthService } from '../_service/auth.service';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  },
  green: {
    primary: '#1dda17',
    secondary: '#ceffcc'
  }
};

@Component({
  selector: 'app-callendar-doc',
  templateUrl: './callendar-doc.component.html',
  styleUrls: ['./callendar-doc.component.css'],
  providers: [
    {
      provide: CalendarDateFormatter,
      useClass: CustomDateFormatter,
    },
  ]
})
export class CallendarDocComponent {

  view: string = 'month';
  locale: string = 'pl';
  weekStartsOn: number = DAYS_OF_WEEK.MONDAY;
  weekendDays: number[] = [DAYS_OF_WEEK.SATURDAY, DAYS_OF_WEEK.SUNDAY];
  viewDate: Date = new Date();
  modalData: {
    action: string;
    event: CalendarEvent;
  };
  modalRef;
  refresh: Subject<any> = new Subject();
  activeDayIsOpen: boolean = false;
  clickedDate: any;
  clickedTime: any;



  events$: Observable<Array<CalendarEvent<{ appointment: Appointment }>>>;
  events: any;
  event: any = {
    date: "",
    time: "",
  }

  treatments: any;
  choosenTreat: any;

  email = "test";

  modalHeader: string;
  modalBody: string;
  closeResult: string;

  constructor(
    private modal: NgbModal,
    private beCom: BEComService,
    private dbService: DatabaseService,
    private authService: AuthService
  ) {
    // this.beCom.getAppointments().subscribe(res => {
    //   this.events = res.json()
    //   console.log(this.events);
    //   console.log(res);
            
    //   this.events$ = this.events
    //     .map((appointment) => this.fromAppointmentsToEvents(appointment));
    // });

    // this.beCom.getTreatments().subscribe(res => {
    //   this.treatments = res.json();
    // });

    this.events$ = this.dbService.getUsers()
    .map((users) =>
      users.filter((user) => this.authService.authState.email == user.email)[0]
    )
    .map((user) => user.flag)
    .switchMap((userType) =>
      userType == 0
        ? this.dbService.userAppointments
        : this.dbService.userAppointments
    )
    .map(this.fromAppointmentsToEvents);

  this.treatments = this.dbService.getZabiegi();
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;
      this.event.date = date;
      this.view = 'day';
    }
  }

  timeClicked(time: Date): void {
    if (time.getHours() >= 8 && time.getHours() <= 18) {
      this.clickedDate = time.getTime();
      this.clickedTime = time.toLocaleTimeString();
      this.event.date = time.toLocaleDateString();
      this.event.time = time.toLocaleTimeString();
      this.view = 'month';
    }
    else {
      alert("Wybrano złą godzinę. Proszę wybrać godzinę między 8:00 a 19:00");
    }
  }

  eventTimesChanged({
    event,
    newStart,
  }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    this.refresh.next();
  }

  handleEvent(action: string, event: CalendarEvent, content): void {
    this.modalData = { event, action };
    this.modalHeader = "Wizyta dnia " + event.start.toLocaleDateString() + " o godzinie " + event.meta.appointment.time + " na zabieg: " + event.meta.appointment.treatName;
    if (event.meta.appointment.flag == 0) {
      this.modalBody = "Czy chcesz usunąć wizitę?"
      this.openModal(content);
    } else if (event.meta.appointment.flag == 1) {
      this.modalBody = "Czy chcesz przyjąć rezerwacje?"
      this.openModal(content);
    } else if (event.meta.appointment.flag == 2) {
      this.modalBody = "Czy chcesz odwołać przyjęcie rezerwacji?"
      this.openModal(content);
    }
  }

  openModal(content) {
    this.modalRef = this.modal.open(content, { centered: true } )
  }

  manageEvent(){
    if (this.modalData.event.meta.appointment.flag == 0) {
      this.dbService.deleteAppointment(this.modalData.event.meta.appointment.key);
      this.modalRef.close();
    } else if (this.modalData.event.meta.appointment.flag == 1) {
      this.dbService.acceptAppointment(this.modalData.event.meta.appointment.key);
      this.modalRef.close();
    } else if (this.modalData.event.meta.appointment.flag == 2) {
      this.dbService.updateAppointment(this.modalData.event.meta.appointment.key, "");
      this.modalRef.close();
    }
  }

  addEvent(form): void {
    var event = {
      date: this.clickedDate,
      time: this.clickedTime,
      treatName: this.choosenTreat,
      doctorEmail: this.authService.authState.email
    }

    if (form.valid) {
      if (this.choosenTreat == null) {
        alert("Błąd, brak zabiegu");
      } else {
        this.dbService.newAppointment(event);
        alert("Ustaliłeś wolny termin na " + this.event.date + " o godzinie " + this.clickedTime);
      }
    }
    else {
      alert("Błąd, wybierz termin");
    }
  }

  fromAppointmentsToEvents(appiontments: Appointment[]) {
    return appiontments.map((appointment: Appointment) => {
      if (appointment.flag == 0) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.green,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 1) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.yellow,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 2) {
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
          color: colors.blue,
          meta: {
            appointment
          }
        })
      }

    })
  }
}

