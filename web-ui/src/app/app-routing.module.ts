import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CallendarComponent } from './callendar/callendar.component';
import { DocListComponent } from './doc-list/doc-list.component';
import { TreatListComponent } from './treat-list/treat-list.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { CallendarDocComponent } from './callendar-doc/callendar-doc.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'callendar', component: CallendarComponent, canActivate: [AuthGuard]},
  {path: 'callendarDoc', component: CallendarDocComponent, canActivate: [AuthGuard]},
  {path: 'docList', component: DocListComponent, canActivate: [AuthGuard]},
  {path: 'treatList', component: TreatListComponent, canActivate: [AuthGuard]},
  {path: 'editProfile', component: EditProfileComponent, canActivate: [AuthGuard]},
]

@NgModule({
  exports: [
    RouterModule
  ],
  imports: [
    RouterModule.forRoot(routes)
  ],
})
export class AppRoutingModule { }
