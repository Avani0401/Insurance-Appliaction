import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NavbarModule, WavesModule, ButtonsModule, MDBBootstrapModule, CardsModule, InputsModule, TableModule } from 'angular-bootstrap-md'

import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { HomeComponent } from './pages/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { UnderwriterDashboardComponent } from './pages/underwriter-dashboard/underwriter-dashboard.component';
import { AuthGuard } from './_auth/auth.guard';
import { AuthInterceptor } from './_auth/auth.interceptors';
import { UserService } from './_services/user.service';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { InfoModalComponent, PolicyComponent } from './components/policy/policy.component';
import { RouterModule } from '@angular/router';
import {MatListModule} from '@angular/material/list';
import { UsersListComponent } from './pages/users-list/users-list.component';
import { UnderwritersListComponent } from './pages/underwriters-list/underwriters-list.component';
import { ClaimListComponent } from './pages/claim-list/claim-list.component';
import {MatTabsModule} from '@angular/material/tabs';
import { AddPolicyComponent } from './components/add-policy/add-policy.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ClaimFormComponent } from './pages/claim-form/claim-form.component';
import { InfoPolicyComponent, PolicyListComponent } from './pages/policy-list/policy-list.component'
import { MatNativeDateModule } from '@angular/material/core';
import { ClaimInfoComponent, ClaimUserComponent } from './pages/claim-user/claim-user.component';
import { NomineeClaimComponent } from './pages/nominee-claim/nominee-claim.component';
import { FooterComponent } from './components/footer/footer.component';
import { ServerDownComponent } from './components/server-down/server-down.component';
import { SidebarModule } from 'ng-sidebar';
import { PolicyWizardComponent } from './pages/policy-wizard/policy-wizard.component';
import {MatStepperModule} from '@angular/material/stepper';
import {MatPaginatorModule} from '@angular/material/paginator';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginComponent,
    SignupComponent,
    ForbiddenComponent,
    UnderwriterDashboardComponent,
    PolicyComponent,
    UsersListComponent,
    UnderwritersListComponent,
    ClaimListComponent,
    AddPolicyComponent,
    InfoModalComponent,
    ClaimFormComponent,
    PolicyListComponent,
    InfoPolicyComponent,
    ClaimUserComponent,
    ClaimInfoComponent,
    NomineeClaimComponent,
    FooterComponent,
    ServerDownComponent,
    PolicyWizardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,

    MDBBootstrapModule.forRoot(),
    NavbarModule,
    WavesModule,
    ButtonsModule,
    CardsModule,
    InputsModule,
    TableModule,
    SidebarModule.forRoot(),

    MatInputModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatIconModule,
    MatGridListModule,
    MatDialogModule,
    MatToolbarModule,
    MatMenuModule,
    MatListModule,
    MatTabsModule,
    MatSelectModule,
    MatDatepickerModule,
    MatSnackBarModule,
    MatNativeDateModule,
    MatStepperModule,
    MatPaginatorModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    UserService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
