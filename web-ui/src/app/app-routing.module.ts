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

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full' },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomeComponent},
  {path: 'callendar', component: CallendarComponent},
  {path: 'callendarDoc', component: CallendarDocComponent},
  {path: 'docList', component: DocListComponent},
  {path: 'treatList', component: TreatListComponent},
  {path: 'editProfile', component: EditProfileComponent},
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
