import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PolicyWizardComponent } from './policy-wizard.component';

describe('PolicyWizardComponent', () => {
  let component: PolicyWizardComponent;
  let fixture: ComponentFixture<PolicyWizardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PolicyWizardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PolicyWizardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
