import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimUserComponent } from './claim-user.component';

describe('ClaimUserComponent', () => {
  let component: ClaimUserComponent;
  let fixture: ComponentFixture<ClaimUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClaimUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClaimUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
