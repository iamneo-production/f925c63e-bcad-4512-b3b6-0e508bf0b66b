import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { LoginComponent } from './pages/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { HomeComponent } from './pages/user/home/home.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { APIInterceptor } from './utils/APIInterceptor';
import { UserGuard } from './guard/user.guard';
import { AdminHomeComponent } from './pages/admin/home/home.component';
import { JwtModule } from '@auth0/angular-jwt';
import { BASE_URL } from './utils/values';
import { AdminGuard } from './guard/admin.guard';
import { AddemployeeformComponent } from './components/addemployeeform/addemployeeform.component';
import { ModalComponent } from './components/modal/modal.component';
import { UserviewComponent } from './components/userview/userview.component';
import { AdminRoutesComponent } from './pages/admin/routes/routes.component';
import { CardRouteComponent } from './components/card-route/card-route.component';
import { RouteFormComponent } from './components/route-form/route-form.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: '', component: HomeComponent, canActivate: [UserGuard] },
  { path: 'admin', component: AdminHomeComponent, canActivate: [AdminGuard] },
  {
    path: 'admin/routes',
    component: AdminRoutesComponent,
    canActivate: [AdminGuard],
  },
];

export function tokenGetter() {
  return localStorage.getItem('jwt');
}

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    AdminHomeComponent,
    AddemployeeformComponent,
    ModalComponent,
    UserviewComponent,
    AdminRoutesComponent,
    CardRouteComponent,
    RouteFormComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        skipWhenExpired: true,
        allowedDomains: [BASE_URL],
        disallowedRoutes: [`${BASE_URL}/login`, `${BASE_URL}/signup`],
      },
    }),
    RouterModule.forRoot(routes),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: APIInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
