import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisponibilityFilterComponent } from './disponibility-filter.component';

describe('DisponibilityFilterComponent', () => {
  let component: DisponibilityFilterComponent;
  let fixture: ComponentFixture<DisponibilityFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisponibilityFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisponibilityFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
