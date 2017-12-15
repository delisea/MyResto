import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageSizeFilterComponent } from './pageSize-filter.component';

describe('PageSizeFilterComponent', () => {
  let component: PageSizeFilterComponent;
  let fixture: ComponentFixture<PageSizeFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageSizeFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageSizeFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
