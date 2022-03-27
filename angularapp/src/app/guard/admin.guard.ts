import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { tokenGetter } from '../app.module';
import { AuthService, ROLE } from '../services/auth.service';
import { roles } from '../utils/values';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(
    private router: Router,
    private jwtHelper: JwtHelperService,
    private authService: AuthService
  ) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (!tokenGetter()) {
      this.authService.role = null;
      this.router.navigate(['/login']);
    }
    const { role }: { role: ROLE } = this.jwtHelper.decodeToken(tokenGetter());
    if (
      !this.jwtHelper.isTokenExpired(tokenGetter()) &&
      role == roles.ROLE_ADMIN
    ) {
      this.authService.role = roles.ROLE_ADMIN;
      return true;
    }
    this.authService.role = null;
    this.router.navigate(['/login']);
  }
}
