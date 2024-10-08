import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = (route, state) => {
  const router=inject(Router);

  if (localStorage.getItem("userToken")===null){
    router.navigate(["/login"]);
    return false;
  }
  return true;
};
