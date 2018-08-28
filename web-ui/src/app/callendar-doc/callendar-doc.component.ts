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
  event: any = {
    date: "",
    time: "",
  }

  treatments: any;
  choosenTreat: any;

  email = "test";

  constructor(
    private modal: NgbModal,
    private beCom: BEComService,
    private http: Http
  ) {
    this.treatments = this.beCom.getTreatments();
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

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    console.log(event);

  }

  addEvent(): void {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    var event = {
      date: this.clickedDate,
      time: this.clickedTime,
      treatName: this.choosenTreat,
      doctorEmail: this.email
    }
    console.log(event);

    this.http.post("https://tim-front2.herokuapp.com/api/appointments/post", event, options)
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