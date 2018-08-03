import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TreatListComponent } from './treat-list.component';

describe('TreatListComponent', () => {
  let component: TreatListComponent;
  let fixture: ComponentFixture<TreatListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TreatListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TreatListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
