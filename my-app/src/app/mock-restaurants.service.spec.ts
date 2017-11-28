import { TestBed, inject } from '@angular/core/testing';

import { MockRestaurantsService } from './mock-restaurants.service';

describe('MockRestaurantsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MockRestaurantsService]
    });
  });

  it('should be created', inject([MockRestaurantsService], (service: MockRestaurantsService) => {
    expect(service).toBeTruthy();
  }));
});
