import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FrenchvalidationComponent } from './frenchvalidation.component';

describe('FrenchvalidationComponent', () => {
  let component: FrenchvalidationComponent;
  let fixture: ComponentFixture<FrenchvalidationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FrenchvalidationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FrenchvalidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
