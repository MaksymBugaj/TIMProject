import { TestBed, inject } from '@angular/core/testing';

import { BEComService } from './becom.service';

describe('BEComService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BEComService]
    });
  });

  it('should be created', inject([BEComService], (service: BEComService) => {
    expect(service).toBeTruthy();
  }));
});
