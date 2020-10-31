import { TestBed } from '@angular/core/testing';

import { DemandserviceService } from './demandservice.service';

describe('DemandserviceService', () => {
  let service: DemandserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DemandserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
