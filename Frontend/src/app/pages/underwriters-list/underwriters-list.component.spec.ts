import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnderwritersListComponent } from './underwriters-list.component';

describe('UnderwritersListComponent', () => {
  let component: UnderwritersListComponent;
  let fixture: ComponentFixture<UnderwritersListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnderwritersListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnderwritersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
