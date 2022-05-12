import { TestBed } from '@angular/core/testing';

import { DashboardUnderwriterService } from './dashboard-underwriter.service';

describe('DashboardUnderwriterService', () => {
  let service: DashboardUnderwriterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DashboardUnderwriterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
