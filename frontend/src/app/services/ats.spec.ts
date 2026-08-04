import { TestBed } from '@angular/core/testing';

import { Ats } from './ats';

describe('Ats', () => {
  let service: Ats;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Ats);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
