import { TestBed } from '@angular/core/testing';

import { ClaimUserService } from './claim-user.service';

describe('ClaimUserService', () => {
  let service: ClaimUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClaimUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
