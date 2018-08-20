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
import { Subject } from 'rxjs';
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
  events: CalendarEvent[] = [];
  activeDayIsOpen: boolean = false;

  clickedDate: Date = new Date();


  constructor(private modal: NgbModal) { }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;
      this.view = 'day';
    }
  }

  timeClicked( time: Date ): void {
    if (time.getHours() >= 8 && time.getHours() <= 18) {
      this.clickedDate = time;
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
    this.events.push({
      title: 'New event',
      start: this.clickedDate,
      color: colors.red,
      draggable: true,
      resizable: {
        beforeStart: true,
        afterEnd: true
      }
    });
    this.refresh.next();
  }
}