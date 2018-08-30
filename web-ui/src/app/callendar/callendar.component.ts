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
  changeDetection: ChangeDetectionStrategy.OnPush,
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
  weekendDays: number[] = [DAYS_OF_WEEK.FRIDAY, DAYS_OF_WEEK.SATURDAY];
  viewDate: Date = new Date();
  modalData: {
    action: string;
    event: CalendarEvent;
  };
  refresh: Subject<any> = new Subject();
  activeDayIsOpen: boolean = false;
  clickedDate: Date = new Date();

  events$: Observable<Array<CalendarEvent<{ appointment: Appointment }>>>;

  treatments: any = [{}];
  choosenTreat: any;
  doctors: any = [{}];
  choosenDoctor: any;
  userEmail: String;


  constructor(
    private modal: NgbModal,
    private beCom: BEComService
  ) {

    this.beCom.getDoctors().subscribe(res => {
      this.doctors = res.json();
      console.log(res);
      console.log(this.doctors);
    });

    this.beCom.getTreatments().subscribe(res => {
      this.treatments = res.json();
      console.log(res);
      console.log(this.treatments);
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

  fromAppointmentsToEvents(appiontments: Appointment[]) {
    return appiontments.map((appointment: Appointment) => {
      if (appointment.flag == 0 && appointment.userEmail == "") {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.green,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 1 && appointment.userEmail == this.userEmail) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.cyan,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 2 && appointment.userEmail == this.userEmail) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.blue,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 1 && appointment.userEmail != this.userEmail) {
        return ({
          title: appointment.treatName,
          start: new Date(appointment.date),
          color: colors.yellow,
          meta: {
            appointment
          }
        })
      } else if (appointment.flag == 2 && appointment.userEmail != this.userEmail) {
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