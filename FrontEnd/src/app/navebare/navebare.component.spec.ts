import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavebareComponent } from './navebare.component';

describe('NavebareComponent', () => {
  let component: NavebareComponent;
  let fixture: ComponentFixture<NavebareComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavebareComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavebareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
