import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CallendarDocComponent } from './callendar-doc.component';

describe('CallendarDocComponent', () => {
  let component: CallendarDocComponent;
  let fixture: ComponentFixture<CallendarDocComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CallendarDocComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CallendarDocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
