import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecialityFilterComponent } from './speciality-filter.component';

describe('SpecialityFilterComponent', () => {
  let component: SpecialityFilterComponent;
  let fixture: ComponentFixture<SpecialityFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpecialityFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecialityFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
