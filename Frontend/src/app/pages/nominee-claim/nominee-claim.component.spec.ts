import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NomineeClaimComponent } from './nominee-claim.component';

describe('NomineeClaimComponent', () => {
  let component: NomineeClaimComponent;
  let fixture: ComponentFixture<NomineeClaimComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NomineeClaimComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NomineeClaimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
