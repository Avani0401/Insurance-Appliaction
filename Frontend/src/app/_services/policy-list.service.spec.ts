import { TestBed } from '@angular/core/testing';

import { PolicyListService } from './policy-list.service';

describe('PolicyListService', () => {
  let service: PolicyListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PolicyListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
