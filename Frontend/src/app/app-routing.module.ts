import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { UnderwriterDashboardComponent } from './pages/underwriter-dashboard/underwriter-dashboard.component';
import { AuthGuard } from './_auth/auth.guard';
import { PolicyComponent } from './components/policy/policy.component';
import { ClaimListComponent } from './pages/claim-list/claim-list.component';
import { UnderwritersListComponent } from './pages/underwriters-list/underwriters-list.component';
import { UsersListComponent } from './pages/users-list/users-list.component';
import { AddPolicyComponent } from './components/add-policy/add-policy.component';
import { PolicyListComponent } from './pages/policy-list/policy-list.component';
import { ClaimFormComponent } from './pages/claim-form/claim-form.component';
import { ClaimUserComponent } from './pages/claim-user/claim-user.component';
import { NomineeClaimComponent } from './pages/nominee-claim/nominee-claim.component';
import { ServerDownComponent } from './components/server-down/server-down.component';
import { PolicyWizardComponent } from './pages/policy-wizard/policy-wizard.component';

const routes: Routes = [
  { path: '', 
  component: HomeComponent},
  
  { path: 'sign-in', component: LoginComponent },
  { path: 'sign-up', component: SignupComponent },
  {
    path: 'policy',
    component: PolicyComponent,
  },
  {
    path: 'claim',
    component: NomineeClaimComponent,
  },
  {
    path: 'underwriter',
    component: UnderwriterDashboardComponent,
    canActivate: [AuthGuard],
    data: { roles: 'Underwriter' },
  },
  {
    path: 'create-policy',
    component: AddPolicyComponent,
    canActivate: [AuthGuard],
    data: { roles: 'Admin' },
  },
  {
    path: 'modify-policy/:policy_id',
    component: AddPolicyComponent,
    canActivate: [AuthGuard],
    data: { roles: 'Admin' },
  },
  {
    path: 'policy-wizard',
    component: PolicyWizardComponent,
    canActivate: [AuthGuard],
    data: { roles: 'User' },
  },
  {
    path: 'policyList/:page',
    component: PolicyListComponent,
    canActivate: [AuthGuard],
    data: { roles: 'User' },
  },
  {
    path: 'claims/all',
    component: ClaimListComponent,
    canActivate: [AuthGuard],
    data: { roles: 'Admin' },
  },
  {
    path: 'your-claims',
    component: ClaimUserComponent,
    canActivate: [AuthGuard],
    data: { roles: 'User' },
  },
  {
    path: 'claimForm',
    component: ClaimFormComponent,
    canActivate: [AuthGuard],
    data: { roles: 'User' },
  },
  {
    path: 'underwriter/all',
    component: UnderwritersListComponent,
    canActivate: [AuthGuard],
    data: { roles: 'Admin' },
  },
  {
    path: 'users/all',
    component: UsersListComponent,
    canActivate: [AuthGuard],
    data: { roles: ['Admin', 'Underwriter'] },
  },
  { path: 'server-down', component: ServerDownComponent },
  { path: 'page-not-found', component: ForbiddenComponent },
  { path: '**', redirectTo: 'page-not-found' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
