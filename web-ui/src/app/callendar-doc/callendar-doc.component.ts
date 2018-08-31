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
  changeDetection: ChangeDetectionStrategy.OnPush,
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
  weekendDays: number[] = [DAYS_OF_WEEK.FRIDAY, DAYS_OF_WEEK.SATURDAY];
  viewDate: Date = new Date();
  modalData: {
    action: string;
    event: CalendarEvent;
  };
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
    private http: Http
  ) {
    this.beCom.getAppointments().subscribe(res => {
      this.events = res.json()
      this.events$ = this.events
        .map((appointment) => this.fromAppointmentsToEvents(appointment));
    });

    this.beCom.getTreatments().subscribe(res => {
      this.treatments = res.json();
    });
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
    console.log(event);
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
    this.modal.open(content, { centered: true } )
  }

  manageEvent(){
    if (this.modalData.event.meta.appointment.flag == 0) {
      this.beCom.deleteAppointment(this.modalData.event.meta.appointment.key);
    } else if (this.modalData.event.meta.appointment.flag == 1) {
      this.beCom.acceptAppointment(this.modalData.event.meta.appointment.key);
    } else if (this.modalData.event.meta.appointment.flag == 2) {
      this.beCom.rejectAppointment(this.modalData.event.meta.appointment.key);
    }
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  addEvent(form): void {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    var event = {
      date: this.clickedDate,
      time: this.clickedTime,
      treatName: this.choosenTreat,
      doctorEmail: this.email
    }
    console.log(event);

    if (form.valid) {
      if (this.choosenTreat == null) {
        console.log("błąd, brak zabiegu");
      } else {
        this.http.post("https://tim-front2.herokuapp.com/api/appointments", event, options)
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
    else {
      console.log("Błąd");
    }
  }

  fromAppointmentsToEvents(appiontment: Appointment) {
    if (appiontment.flag == 0) {
      return ({
        title: appiontment.treatName,
        start: new Date(appiontment.date),
        color: colors.green,
        meta: {
          appiontment
        }
      })
    } else if (appiontment.flag == 1) {
      return ({
        title: appiontment.treatName,
        start: new Date(appiontment.date),
        color: colors.yellow,
        meta: {
          appiontment
        }
      })
    } else if (appiontment.flag == 2) {
      return ({
        title: appiontment.treatName,
        start: new Date(appiontment.date),
        color: colors.red,
        meta: {
          appiontment
        }
      })
    } else {
      return ({
        title: "test",
        start: new Date(appiontment.date),
        color: colors.blue,
        meta: {
          appiontment
        }
      })
    }
  }

}