import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalisationFilterComponent } from './localisation-filter.component';

describe('LocalisationFilterComponent', () => {
  let component: LocalisationFilterComponent;
  let fixture: ComponentFixture<LocalisationFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LocalisationFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LocalisationFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
