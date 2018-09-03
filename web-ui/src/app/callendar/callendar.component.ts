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

  events$: Observable<Array<CalendarEvent<{ appointment: Appointment }>>>;
  events: any;

  treatments: any = [{}];
  choosenTreat: any;
  doctors: any = [{}];
  choosenDoctor: any;
  userEmail: String;

  modalHeader: string;
  modalBody: string;
  closeResult: string;

  constructor(
    private modal: NgbModal,
    private beCom: BEComService,
    private changeDetectorRef: ChangeDetectorRef
  ) {
    this.beCom.getAppointments().subscribe(res => {
      this.events = res.json()
      this.events$ = this.events
        .map((appointment) => this.fromAppointmentsToEvents(appointment));
    });


    this.beCom.getDoctors().subscribe(res => {
      this.doctors = res.json();
    });

    this.beCom.getTreatments().subscribe(res => {
      this.treatments = res.json();
    });

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

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    console.log(event);

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

  fromAppointmentsToEvents(appiontment: Appointment) {
    if (appiontment.flag == 0 && appiontment.userEmail == "") {
      return ({
        title: appiontment.treatName,
        start: new Date(appiontment.date),
        color: colors.green,
        meta: {
          appiontment
        }
      })
    } else if (appiontment.flag == 1 && appiontment.userEmail == this.userEmail) {
      return ({
        title: appiontment.treatName,
        start: new Date(appiontment.date),
        color: colors.cyan,
        meta: {
          appiontment
        }
      })
    } else if (appiontment.flag == 2 && appiontment.userEmail == this.userEmail) {
      return ({
        title: appiontment.treatName,
        start: new Date(appiontment.date),
        color: colors.blue,
        meta: {
          appiontment
        }
      })
    } else if (appiontment.flag == 1 && appiontment.userEmail != this.userEmail) {
      return ({
        title: appiontment.treatName,
        start: new Date(appiontment.date),
        color: colors.yellow,
        meta: {
          appiontment
        }
      })
    } else if (appiontment.flag == 2 && appiontment.userEmail != this.userEmail) {
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
        color: colors.black,
        meta: {
          appiontment
        }
      })
    }
  }
}