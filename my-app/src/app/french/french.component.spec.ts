import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FrenchComponent } from './french.component';

describe('DateFilterComponent', () => {
  let component: FrenchComponent;
  let fixture: ComponentFixture<FrenchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FrenchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FrenchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
