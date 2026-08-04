import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Ats } from './ats';

describe('Ats', () => {
  let component: Ats;
  let fixture: ComponentFixture<Ats>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Ats],
    }).compileComponents();

    fixture = TestBed.createComponent(Ats);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
